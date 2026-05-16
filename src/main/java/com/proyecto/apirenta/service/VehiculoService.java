package com.proyecto.apirenta.service;

import com.proyecto.apirenta.dto.VehiculoDTO;
import com.proyecto.apirenta.exception.BadRequestException;
import com.proyecto.apirenta.exception.ResourceNotFoundException;
import com.proyecto.apirenta.model.Vehiculo;
import com.proyecto.apirenta.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    @Transactional(readOnly = true)
    public List<VehiculoDTO> obtenerTodos() {
        return vehiculoRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public VehiculoDTO obtenerPorId(Long id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo", "id", id));
        return convertirADTO(vehiculo);
    }

    @Transactional(readOnly = true)
    public List<VehiculoDTO> obtenerDisponibles() {
        return vehiculoRepository.findByDisponible(true).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<VehiculoDTO> obtenerPorTipo(Vehiculo.TipoVehiculo tipo) {
        return vehiculoRepository.findByTipo(tipo).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public VehiculoDTO crear(VehiculoDTO vehiculoDTO) {
        // Validar que la placa no exista
        if (vehiculoRepository.existsByPlaca(vehiculoDTO.getPlaca())) {
            throw new BadRequestException("Ya existe un vehículo con la placa: " + vehiculoDTO.getPlaca());
        }

        Vehiculo vehiculo = convertirAEntidad(vehiculoDTO);
        vehiculo.setDisponible(true); // Por defecto disponible
        Vehiculo vehiculoGuardado = vehiculoRepository.save(vehiculo);

        return convertirADTO(vehiculoGuardado);
    }

    @Transactional
    public VehiculoDTO actualizar(Long id, VehiculoDTO vehiculoDTO) {
        Vehiculo vehiculoExistente = vehiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo", "id", id));

        // Validar que la placa no esté siendo usada por otro vehículo
        if (!vehiculoExistente.getPlaca().equals(vehiculoDTO.getPlaca())
                && vehiculoRepository.existsByPlaca(vehiculoDTO.getPlaca())) {
            throw new BadRequestException("Ya existe un vehículo con la placa: " + vehiculoDTO.getPlaca());
        }

        // Actualizar campos
        vehiculoExistente.setPlaca(vehiculoDTO.getPlaca());
        vehiculoExistente.setMarca(vehiculoDTO.getMarca());
        vehiculoExistente.setModelo(vehiculoDTO.getModelo());
        vehiculoExistente.setAnio(vehiculoDTO.getAnio());
        vehiculoExistente.setColor(vehiculoDTO.getColor());
        vehiculoExistente.setPrecioDiario(vehiculoDTO.getPrecioDiario());
        vehiculoExistente.setTipo(vehiculoDTO.getTipo());

        if (vehiculoDTO.getDisponible() != null) {
            vehiculoExistente.setDisponible(vehiculoDTO.getDisponible());
        }

        Vehiculo vehiculoActualizado = vehiculoRepository.save(vehiculoExistente);
        return convertirADTO(vehiculoActualizado);
    }

    @Transactional
    public void eliminar(Long id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo", "id", id));

        // Validar que no tenga reservas activas
        boolean tieneReservasActivas = vehiculo.getReservas().stream()
                .anyMatch(r -> r.getEstado() == com.proyecto.apirenta.model.Reserva.EstadoReserva.ACTIVA);

        if (tieneReservasActivas) {
            throw new BadRequestException("No se puede eliminar el vehículo porque tiene reservas activas");
        }

        vehiculoRepository.delete(vehiculo);
    }

    // Métodos auxiliares de conversión
    private VehiculoDTO convertirADTO(Vehiculo vehiculo) {
        VehiculoDTO dto = new VehiculoDTO();
        dto.setId(vehiculo.getId());
        dto.setPlaca(vehiculo.getPlaca());
        dto.setMarca(vehiculo.getMarca());
        dto.setModelo(vehiculo.getModelo());
        dto.setAnio(vehiculo.getAnio());
        dto.setColor(vehiculo.getColor());
        dto.setPrecioDiario(vehiculo.getPrecioDiario());
        dto.setDisponible(vehiculo.getDisponible());
        dto.setTipo(vehiculo.getTipo());
        return dto;
    }

    private Vehiculo convertirAEntidad(VehiculoDTO dto) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca(dto.getPlaca());
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setAnio(dto.getAnio());
        vehiculo.setColor(dto.getColor());
        vehiculo.setPrecioDiario(dto.getPrecioDiario());
        vehiculo.setDisponible(dto.getDisponible() != null ? dto.getDisponible() : true);
        vehiculo.setTipo(dto.getTipo());
        return vehiculo;
    }
}
