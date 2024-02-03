package org.example.documents;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Usuario {
    String id;
    String nombre;
    String password;
    List<Producto> productos;

}
