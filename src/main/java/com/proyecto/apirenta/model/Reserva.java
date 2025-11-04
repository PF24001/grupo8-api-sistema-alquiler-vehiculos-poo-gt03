package com.proyecto.apirenta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombreCliente;

    @Column(nullable = false, length = 20)
    private String dpiCliente;

    @Column(length = 15)
    private String telefonoCliente;

    @Column(length = 100)
    private String emailCliente;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @Column(nullable = false)
    private BigDecimal costoTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoReserva estado = EstadoReserva.ACTIVA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    public enum EstadoReserva {
        ACTIVA,
        COMPLETADA,
        CANCELADA
    }
}
