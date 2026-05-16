package com.proyecto.apirenta.controller;

import com.proyecto.apirenta.dto.ReservaDTO;
import com.proyecto.apirenta.model.Reserva;
import com.proyecto.apirenta.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
@Validated
public class ReservaController {

    private final ReservaService reservaService;

    /**
     * GET /api/reservas - Obtener todas las reservas
     */
    @GetMapping
    public ResponseEntity<List<ReservaDTO>> obtenerTodas() {
        List<ReservaDTO> reservas = reservaService.obtenerTodas();
        return ResponseEntity.ok(reservas);
    }

    /**
     * GET /api/reservas/{id} - Obtener una reserva por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> obtenerPorId(@PathVariable Long id) {
        ReservaDTO reserva = reservaService.obtenerPorId(id);
        return ResponseEntity.ok(reserva);
    }

    /**
     * GET /api/reservas/vehiculo/{vehiculoId} - Obtener reservas por vehículo
     */
    @GetMapping("/vehiculo/{vehiculoId}")
    public ResponseEntity<List<ReservaDTO>> obtenerPorVehiculo(@PathVariable Long vehiculoId) {
        List<ReservaDTO> reservas = reservaService.obtenerPorVehiculo(vehiculoId);
        return ResponseEntity.ok(reservas);
    }

    /**
     * GET /api/reservas/estado/{estado} - Obtener reservas por estado
     */
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ReservaDTO>> obtenerPorEstado(@PathVariable Reserva.EstadoReserva estado) {
        List<ReservaDTO> reservas = reservaService.obtenerPorEstado(estado);
        return ResponseEntity.ok(reservas);
    }

    /**
     * POST /api/reservas - Crear una nueva reserva
     */
    @PostMapping
    public ResponseEntity<ReservaDTO> crear(@Valid @RequestBody ReservaDTO reservaDTO) {
        ReservaDTO nuevaReserva = reservaService.crear(reservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
    }

    /**
     * PUT /api/reservas/{id} - Actualizar una reserva existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ReservaDTO reservaDTO) {
        ReservaDTO reservaActualizada = reservaService.actualizar(id, reservaDTO);
        return ResponseEntity.ok(reservaActualizada);
    }

    /**
     * PATCH /api/reservas/{id}/cancelar - Cancelar una reserva
     */
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ReservaDTO> cancelar(@PathVariable Long id) {
        ReservaDTO reservaCancelada = reservaService.cancelar(id);
        return ResponseEntity.ok(reservaCancelada);
    }

    /**
     * PATCH /api/reservas/{id}/completar - Completar una reserva
     */
    @PatchMapping("/{id}/completar")
    public ResponseEntity<ReservaDTO> completar(@PathVariable Long id) {
        ReservaDTO reservaCompletada = reservaService.completar(id);
        return ResponseEntity.ok(reservaCompletada);
    }

    /**
     * DELETE /api/reservas/{id} - Eliminar una reserva
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reservaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
