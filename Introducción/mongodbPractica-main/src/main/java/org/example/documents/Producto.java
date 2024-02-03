package org.example.documents;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Producto {
    String id;
    String nombre_producto;
    Double precio;
    List<Comentario> comentarios;
    List<Producto> productos;
}