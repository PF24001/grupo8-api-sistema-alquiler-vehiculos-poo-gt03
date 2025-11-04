package com.proyecto.apirenta.dto;

import com.proyecto.apirenta.model.Reserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

    private Long id;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombreCliente;

    @NotBlank(message = "El DPI del cliente es obligatorio")
    @Size(min = 13, max = 20, message = "El DPI debe tener entre 13 y 20 caracteres")
    @Pattern(regexp = "^[0-9]+$", message = "El DPI solo puede contener números")
    private String dpiCliente;

    @Size(max = 15, message = "El teléfono no puede exceder 15 caracteres")
    @Pattern(regexp = "^[0-9+-]*$", message = "El teléfono solo puede contener números, + y -")
    private String telefonoCliente;

    @Email(message = "El email debe ser válido")
    @Size(max = 100, message = "El email no puede exceder 100 caracteres")
    private String emailCliente;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @FutureOrPresent(message = "La fecha de inicio debe ser hoy o en el futuro")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    @Future(message = "La fecha de fin debe ser en el futuro")
    private LocalDate fechaFin;

    private BigDecimal costoTotal;

    private Reserva.EstadoReserva estado;

    @NotNull(message = "El ID del vehículo es obligatorio")
    private Long vehiculoId;

    // Información adicional del vehículo (solo para lectura)
    private String vehiculoPlaca;
    private String vehiculoMarca;
    private String vehiculoModelo;
}
