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
@Table(name = "avion")
public class Avion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avion_id")
    private Integer id;
    private String modelo;
    @Column(name = "capacidad_pasajeros")
    private int capacidadPasajeros;
    @ManyToOne
    @JoinColumn(name = "aerolinea_id_fk")
    private Aerolinea aerolinea;
    private boolean estado;
}
