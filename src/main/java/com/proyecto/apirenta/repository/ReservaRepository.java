package com.proyecto.apirenta.repository;

import com.proyecto.apirenta.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByVehiculoId(Long vehiculoId);

    List<Reserva> findByEstado(Reserva.EstadoReserva estado);

    List<Reserva> findByDpiCliente(String dpiCliente);

    List<Reserva> findByNombreClienteContainingIgnoreCase(String nombreCliente);

    List<Reserva> findByFechaInicioBetween(LocalDate inicio, LocalDate fin);
}
