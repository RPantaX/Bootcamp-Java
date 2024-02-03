package org.example.aerolinea.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserva_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "vuelo_id_fk")
    private Vuelo vuelo;
    @ManyToOne
    @JoinColumn(name = "pasajero_id_fk")
    private Pasajero pasajero;
    @Column(name = "asiento_numero")
    private int numAsiento;
    private boolean estado;
}
