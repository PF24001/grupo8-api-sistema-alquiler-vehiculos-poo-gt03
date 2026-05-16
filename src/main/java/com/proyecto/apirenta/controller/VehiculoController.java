package com.proyecto.apirenta.controller;

import com.proyecto.apirenta.dto.VehiculoDTO;
import com.proyecto.apirenta.model.Vehiculo;
import com.proyecto.apirenta.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
@RequiredArgsConstructor
@Validated
public class VehiculoController {

    private final VehiculoService vehiculoService;

    /**
     * GET /api/vehiculos - Obtener todos los vehículos
     */
    @GetMapping
    public ResponseEntity<List<VehiculoDTO>> obtenerTodos() {
        List<VehiculoDTO> vehiculos = vehiculoService.obtenerTodos();
        return ResponseEntity.ok(vehiculos);
    }

    /**
     * GET /api/vehiculos/{id} - Obtener un vehículo por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<VehiculoDTO> obtenerPorId(@PathVariable Long id) {
        VehiculoDTO vehiculo = vehiculoService.obtenerPorId(id);
        return ResponseEntity.ok(vehiculo);
    }

    /**
     * GET /api/vehiculos/disponibles - Obtener vehículos disponibles
     */
    @GetMapping("/disponibles")
    public ResponseEntity<List<VehiculoDTO>> obtenerDisponibles() {
        List<VehiculoDTO> vehiculos = vehiculoService.obtenerDisponibles();
        return ResponseEntity.ok(vehiculos);
    }

    /**
     * GET /api/vehiculos/tipo/{tipo} - Obtener vehículos por tipo
     */
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<VehiculoDTO>> obtenerPorTipo(@PathVariable Vehiculo.TipoVehiculo tipo) {
        List<VehiculoDTO> vehiculos = vehiculoService.obtenerPorTipo(tipo);
        return ResponseEntity.ok(vehiculos);
    }

    /**
     * POST /api/vehiculos - Crear un nuevo vehículo
     */
    @PostMapping
    public ResponseEntity<VehiculoDTO> crear(@Valid @RequestBody VehiculoDTO vehiculoDTO) {
        VehiculoDTO nuevoVehiculo = vehiculoService.crear(vehiculoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVehiculo);
    }

    /**
     * PUT /api/vehiculos/{id} - Actualizar un vehículo existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<VehiculoDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody VehiculoDTO vehiculoDTO) {
        VehiculoDTO vehiculoActualizado = vehiculoService.actualizar(id, vehiculoDTO);
        return ResponseEntity.ok(vehiculoActualizado);
    }

    /**
     * DELETE /api/vehiculos/{id} - Eliminar un vehículo
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        vehiculoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
