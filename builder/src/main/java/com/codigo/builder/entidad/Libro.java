package com.codigo.builder.entidad;

import java.time.LocalDate;
//BUILDER SIN ANOTACIONES
public class Libro {
    private String titulo;
    private String autor;
    private String isbn;
    private LocalDate fechaPublicacion;

    private Libro(Builder builder){
        this.titulo=builder.titulo;
        this.autor=builder.autor;
        this.isbn=builder.isbn;
        this.fechaPublicacion=builder.fechaPublicacion;
    }
    public static class Builder{
        private String titulo;
        private String autor;
        private String isbn;
        private LocalDate fechaPublicacion;

        public Builder autor(String autor){
            this.autor=autor;
            return this;
        }
        public Builder titulo(String titulo){
            this.titulo=titulo;
            return this;
        }
        public Builder isbn(String isbn){
            this.isbn=isbn;
            return this;
        }
        public Builder fechaPublicacion(LocalDate fechaPublicacion){
            this.fechaPublicacion=fechaPublicacion;
            return this;
        }
        public Libro build(){
            return new Libro(this);
        }
    }
}
