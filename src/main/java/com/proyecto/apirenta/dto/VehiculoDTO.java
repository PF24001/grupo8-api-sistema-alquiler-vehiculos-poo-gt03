package com.proyecto.apirenta.dto;

import com.proyecto.apirenta.model.Vehiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoDTO {

    private Long id;

    @NotBlank(message = "La placa es obligatoria")
    @Size(min = 5, max = 20, message = "La placa debe tener entre 5 y 20 caracteres")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "La placa solo puede contener letras mayúsculas, números y guiones")
    private String placa;

    @NotBlank(message = "La marca es obligatoria")
    @Size(max = 50, message = "La marca no puede exceder 50 caracteres")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    @Size(max = 50, message = "El modelo no puede exceder 50 caracteres")
    private String modelo;

    @NotNull(message = "El año es obligatorio")
    @Min(value = 1900, message = "El año debe ser mayor a 1900")
    @Max(value = 2030, message = "El año no puede ser mayor a 2030")
    private Integer anio;

    @Size(max = 30, message = "El color no puede exceder 30 caracteres")
    private String color;

    @NotNull(message = "El precio diario es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio diario debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El precio diario debe tener máximo 10 dígitos enteros y 2 decimales")
    private BigDecimal precioDiario;

    private Boolean disponible;

    @NotNull(message = "El tipo de vehículo es obligatorio")
    private Vehiculo.TipoVehiculo tipo;
}
