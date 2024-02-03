package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        int[] numeros={1,2,3,4,5};
        System.out.println(numeros[0]);
        try {
            System.out.println(numeros[5]);


        }catch (Exception e){

            System.out.println("error"+ e.getMessage());
        }
        System.out.println("final");
    }
}