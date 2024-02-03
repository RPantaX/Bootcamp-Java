import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Ejercicios hechos por: JEFFERSON ALESSANDRO PANTA RUIZ, CORREO:PANTAJEFFERSON173@GMAIL.COM");
        //Ejercicio 1: Contador de números pares
        //Descripción: Itera sobre los números del 1 al 50 e imprime solo los números pares.
        //
        int numParMax=50;
        System.out.println("Impresión de números pares");
        for(int i=1; i<=numParMax; i++){
            if(i%2==0){
                System.out.println(i);
            }
        }
        //Ejercicio 2: Suma de números impares
        //Descripción: Suma todos los números impares del 1 al 100.
        int numImparMax=100;
        System.out.println("Suma de los 100 primeros números impares");
        int sumaImpares=0;
        for(int i=1; i<=numImparMax; i++){
            if(i%2!=0){
                sumaImpares+=i;
            }
        }
        System.out.println(sumaImpares);

        //Ejercicio 3: Tabla de multiplicar
        //Descripción: Genera la tabla del 5 hasta el producto de 5x10.
        int multiplicador=5;
        System.out.println("Tabla de multiplicar del 5");
        for(int i=0; i<=10; i++){
            System.out.println(multiplicador+" x "+i+" = "+(multiplicador*i));
        }
        //Ejercicio 4: Factorial de un número
        //Descripción: Calcula el factorial de un número ingresado por el usuario. El factorial de un número n
        // (representado como) es el producto de todos los enteros positivos menores o iguales a n.

        System.out.println("Factorial de un número");
        Scanner scanner= new Scanner(System.in);
        System.out.print("Ingrese un número entero: ");

        while (!scanner.hasNextInt()) {
            System.out.print("Ingrese un número entero válido: ");
            scanner.next(); // Consumir la entrada no válida
        }
        int numero= scanner.nextInt();
        int producto=1;
        if(numero==0){
            System.out.println("El factorial de 0 es = 1");
        }
        else{
            for(int i=1; i<=numero;i++){
                producto*=i;
                if(i==numero){
                    System.out.println(i+"="+producto);
                }
                else{
                    System.out.print(i+" x ");
                }
            }
        }

        //Ejercicio 5: Suma de dígitos
        //Descripción: Suma todos los dígitos de un número ingresado por el usuario.
        //No usar la conversión a String para resolver el problema.
        System.out.println("Suma de dígitos");
        System.out.print("Ingresa un número: ");
        int numero2 = scanner.nextInt();
        int num = numero2;
        int sumaTotal=0;
        do{
            num = numero2 % 10;
            sumaTotal=sumaTotal+num;
            numero2 = numero2 / 10;
        }while(numero2>0);
        System.out.println(sumaTotal);

        //Ejercicio 6: Cuadrados perfectos
        //Descripción: Encuentra y muestra todos los cuadrados perfectos hasta 100.
        System.out.println("cuadrados perfectos hasta el número 100");
        int i=1;
        while(i<=10){
            System.out.println(Math.pow(i,2));
            i++;
        }

    }
}