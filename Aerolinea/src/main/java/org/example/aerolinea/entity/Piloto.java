package org.example.aerolinea.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "piloto")
public class Piloto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "piloto_id")
    private Integer id;
    private String nombre;
    private String apellido;
    private boolean estado;

    @ManyToMany
    @JoinTable(name = "piloto_vuelo",
            joinColumns = @JoinColumn(name = "piloto_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "vuelo_id_fk"))
    List<Vuelo> vuelos;

}
