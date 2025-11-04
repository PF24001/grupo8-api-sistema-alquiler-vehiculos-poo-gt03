package com.proyecto.apirenta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehiculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String placa;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(nullable = false)
    private Integer anio;

    @Column(length = 30)
    private String color;

    @Column(nullable = false)
    private BigDecimal precioDiario;

    @Column(nullable = false)
    private Boolean disponible = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoVehiculo tipo;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas = new ArrayList<>();

    public enum TipoVehiculo {
        SEDAN,
        SUV,
        CAMIONETA,
        DEPORTIVO,
        ECONOMICO
    }
}
