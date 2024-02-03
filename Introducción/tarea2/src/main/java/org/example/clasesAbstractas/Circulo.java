package org.example.clasesAbstractas;

import java.sql.SQLOutput;
import java.util.Date;

/*Implementar la clase 'Círculo' basándose en la implementación realizada en clase.
Implementar los métodos de área y perímetro que se heredan de la clase abstracta
'Figura'. Agregue atributos a la clase circulo si es necesario.*/
public class Circulo extends Figura{
    public static final float pi= 3.1415F;
    private float radio;

    public Circulo(String color, float radio) {
        super(color);
        this.radio=radio;
    }

    @Override
    public float area() {
        return (float) (pi*Math.pow(radio,2));
    }

    @Override
    public float perimetro() {
        return 2*pi*radio;
    }
    @Override
    public void printColor()
    {
        System.out.println("El color del circulo es: " + color);
    }
}
