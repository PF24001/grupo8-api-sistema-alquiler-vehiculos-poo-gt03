package com.proyecto.apirenta.repository;

import com.proyecto.apirenta.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    Optional<Vehiculo> findByPlaca(String placa);

    List<Vehiculo> findByDisponible(Boolean disponible);

    List<Vehiculo> findByTipo(Vehiculo.TipoVehiculo tipo);

    List<Vehiculo> findByMarcaContainingIgnoreCase(String marca);

    boolean existsByPlaca(String placa);
}
