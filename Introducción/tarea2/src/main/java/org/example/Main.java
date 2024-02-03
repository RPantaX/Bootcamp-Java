package org.example;


import org.example.clasesAbstractas.Circulo;
import org.example.clasesAbstractas.Cuadrado;

public class Main {
    public static void main(String[] args) {
        Circulo circulo1= new Circulo("rojo",5);
        Cuadrado cuadrado1= new Cuadrado("amarillo",30);

        //DATOS DEL CIRCULO
        System.out.println("----------------------DATOS DEL CÍRCULO-----------------------------------------");
        circulo1.printColor();
        System.out.println("El área del círculo es:"+circulo1.area());
        System.out.println("El perímetro del círculo es:"+circulo1.perimetro());


        //DATOS DEL CUADRADO
        System.out.println("\n\n");
        System.out.println("----------------------DATOS DEL CUADRADO----------------------------------------");
        cuadrado1.printColor();
        System.out.println("El área del cuadrado es:"+cuadrado1.area());
        System.out.println("El perímetro del cuadrado es:"+cuadrado1.perimetro());
    }
}