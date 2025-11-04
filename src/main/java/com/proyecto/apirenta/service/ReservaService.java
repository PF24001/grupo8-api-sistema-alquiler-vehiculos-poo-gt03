package com.proyecto.apirenta.service;

import com.proyecto.apirenta.dto.ReservaDTO;
import com.proyecto.apirenta.exception.BadRequestException;
import com.proyecto.apirenta.exception.ResourceNotFoundException;
import com.proyecto.apirenta.model.Reserva;
import com.proyecto.apirenta.model.Vehiculo;
import com.proyecto.apirenta.repository.ReservaRepository;
import com.proyecto.apirenta.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final VehiculoRepository vehiculoRepository;

    @Transactional(readOnly = true)
    public List<ReservaDTO> obtenerTodas() {
        return reservaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReservaDTO obtenerPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva", "id", id));
        return convertirADTO(reserva);
    }

    @Transactional(readOnly = true)
    public List<ReservaDTO> obtenerPorVehiculo(Long vehiculoId) {
        return reservaRepository.findByVehiculoId(vehiculoId).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReservaDTO> obtenerPorEstado(Reserva.EstadoReserva estado) {
        return reservaRepository.findByEstado(estado).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReservaDTO crear(ReservaDTO reservaDTO) {
        // Validar fechas
        if (reservaDTO.getFechaFin().isBefore(reservaDTO.getFechaInicio())
                || reservaDTO.getFechaFin().isEqual(reservaDTO.getFechaInicio())) {
            throw new BadRequestException("La fecha de fin debe ser posterior a la fecha de inicio");
        }

        // Obtener vehículo
        Vehiculo vehiculo = vehiculoRepository.findById(reservaDTO.getVehiculoId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo", "id", reservaDTO.getVehiculoId()));

        // Validar disponibilidad
        if (!vehiculo.getDisponible()) {
            throw new BadRequestException("El vehículo no está disponible para reserva");
        }

        // Calcular costo total
        long dias = ChronoUnit.DAYS.between(reservaDTO.getFechaInicio(), reservaDTO.getFechaFin());
        BigDecimal costoTotal = vehiculo.getPrecioDiario().multiply(BigDecimal.valueOf(dias));

        // Crear reserva
        Reserva reserva = convertirAEntidad(reservaDTO, vehiculo);
        reserva.setCostoTotal(costoTotal);
        reserva.setEstado(Reserva.EstadoReserva.ACTIVA);

        // Marcar vehículo como no disponible
        vehiculo.setDisponible(false);
        vehiculoRepository.save(vehiculo);

        Reserva reservaGuardada = reservaRepository.save(reserva);
        return convertirADTO(reservaGuardada);
    }

    @Transactional
    public ReservaDTO actualizar(Long id, ReservaDTO reservaDTO) {
        Reserva reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva", "id", id));

        // No permitir actualizar reservas completadas o canceladas
        if (reservaExistente.getEstado() != Reserva.EstadoReserva.ACTIVA) {
            throw new BadRequestException("No se puede actualizar una reserva con estado: " +
                    reservaExistente.getEstado());
        }

        // Validar fechas si se están actualizando
        if (reservaDTO.getFechaFin().isBefore(reservaDTO.getFechaInicio())
                || reservaDTO.getFechaFin().isEqual(reservaDTO.getFechaInicio())) {
            throw new BadRequestException("La fecha de fin debe ser posterior a la fecha de inicio");
        }

        // Actualizar campos
        reservaExistente.setNombreCliente(reservaDTO.getNombreCliente());
        reservaExistente.setDpiCliente(reservaDTO.getDpiCliente());
        reservaExistente.setTelefonoCliente(reservaDTO.getTelefonoCliente());
        reservaExistente.setEmailCliente(reservaDTO.getEmailCliente());
        reservaExistente.setFechaInicio(reservaDTO.getFechaInicio());
        reservaExistente.setFechaFin(reservaDTO.getFechaFin());

        // Recalcular costo si cambiaron las fechas
        long dias = ChronoUnit.DAYS.between(reservaDTO.getFechaInicio(), reservaDTO.getFechaFin());
        BigDecimal costoTotal = reservaExistente.getVehiculo().getPrecioDiario()
                .multiply(BigDecimal.valueOf(dias));
        reservaExistente.setCostoTotal(costoTotal);

        Reserva reservaActualizada = reservaRepository.save(reservaExistente);
        return convertirADTO(reservaActualizada);
    }

    @Transactional
    public ReservaDTO cancelar(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva", "id", id));

        if (reserva.getEstado() != Reserva.EstadoReserva.ACTIVA) {
            throw new BadRequestException("Solo se pueden cancelar reservas activas");
        }

        reserva.setEstado(Reserva.EstadoReserva.CANCELADA);

        // Liberar vehículo
        Vehiculo vehiculo = reserva.getVehiculo();
        vehiculo.setDisponible(true);
        vehiculoRepository.save(vehiculo);

        Reserva reservaActualizada = reservaRepository.save(reserva);
        return convertirADTO(reservaActualizada);
    }

    @Transactional
    public ReservaDTO completar(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva", "id", id));

        if (reserva.getEstado() != Reserva.EstadoReserva.ACTIVA) {
            throw new BadRequestException("Solo se pueden completar reservas activas");
        }

        reserva.setEstado(Reserva.EstadoReserva.COMPLETADA);

        // Liberar vehículo
        Vehiculo vehiculo = reserva.getVehiculo();
        vehiculo.setDisponible(true);
        vehiculoRepository.save(vehiculo);

        Reserva reservaActualizada = reservaRepository.save(reserva);
        return convertirADTO(reservaActualizada);
    }

    @Transactional
    public void eliminar(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva", "id", id));

        // Solo permitir eliminar reservas canceladas
        if (reserva.getEstado() == Reserva.EstadoReserva.ACTIVA) {
            throw new BadRequestException("No se puede eliminar una reserva activa. Primero debe cancelarla.");
        }

        reservaRepository.delete(reserva);
    }

    // Métodos auxiliares de conversión
    private ReservaDTO convertirADTO(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setNombreCliente(reserva.getNombreCliente());
        dto.setDpiCliente(reserva.getDpiCliente());
        dto.setTelefonoCliente(reserva.getTelefonoCliente());
        dto.setEmailCliente(reserva.getEmailCliente());
        dto.setFechaInicio(reserva.getFechaInicio());
        dto.setFechaFin(reserva.getFechaFin());
        dto.setCostoTotal(reserva.getCostoTotal());
        dto.setEstado(reserva.getEstado());
        dto.setVehiculoId(reserva.getVehiculo().getId());
        dto.setVehiculoPlaca(reserva.getVehiculo().getPlaca());
        dto.setVehiculoMarca(reserva.getVehiculo().getMarca());
        dto.setVehiculoModelo(reserva.getVehiculo().getModelo());
        return dto;
    }

    private Reserva convertirAEntidad(ReservaDTO dto, Vehiculo vehiculo) {
        Reserva reserva = new Reserva();
        reserva.setNombreCliente(dto.getNombreCliente());
        reserva.setDpiCliente(dto.getDpiCliente());
        reserva.setTelefonoCliente(dto.getTelefonoCliente());
        reserva.setEmailCliente(dto.getEmailCliente());
        reserva.setFechaInicio(dto.getFechaInicio());
        reserva.setFechaFin(dto.getFechaFin());
        reserva.setVehiculo(vehiculo);
        return reserva;
    }
}
