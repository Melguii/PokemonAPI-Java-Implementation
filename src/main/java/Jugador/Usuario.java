package Jugador;

import java.util.Scanner;

public class Usuario {
    int monedas;            //Empieza con 1000 monedas inciales

    //Constructor Usuario
    public Usuario(){
        monedas = 1000;
    }

    /**
     * Funcion llamada por el menu, donde el usuario pide al programa comprar monedas
     */
    public void comprarMonedas(){
        int monedas;
        double precio = 0;
        char confirmar;

        //Pedimos al usuario cuantas monedas quiere comprar
        do {

            System.out.println("Quantes monedes vol comprar?");
            Scanner scMonedas = new Scanner (System.in);
            monedas = scMonedas.nextInt();

            //Si el usuario mete un valor negadtivo, volvemos a pedir una nueva cantidad
            if (monedas < 0){
                System.out.println("Cal introduir un nombre estrictament positiu.");
            }

        } while (monedas < 0);

        //Controlamos en cual de los intervarlos esta el valor de las monedas y mostramos su precio
        if (monedas < 250){
            precio = monedas * 0.01;

        } else if (250 <= monedas && monedas < 500){
            precio = monedas * 0.009;

        } else if (500 <= monedas && monedas < 1000){
            precio = monedas * 0.007;

        } else if (1000 <= monedas && monedas < 10000){
            precio = monedas * 0.005;

        } else if (10000 <= monedas){
            precio = monedas * 0.0025;

        }

        //Mostramos precio de las monedas en euros y le pedimos al usuario que confirme su compra
        do {

            System.out.println("El preu total és de" + precio + "€. Confirma la compra? (Y/N)");
            Scanner scConfirmar = new Scanner (System.in);
            confirmar = scConfirmar.next().charAt(0);

            //En caso de introducir datos no correctos volvemos a pedirlos hasta que el usuario
            //introduzca una opción correcta
            if (confirmar != 'Y' || confirmar != 'y' || confirmar != 'N' || confirmar != 'n'){
                System.out.println("Error, opció no correcte. Confirma la compra? (Y/N)");
            }

        } while (confirmar != 'Y' || confirmar != 'y' || confirmar != 'N' || confirmar != 'n');

        if (confirmar == 'Y' || confirmar == 'y') {
            System.out.println("S'han afegit" + monedas + "monedes al seu compte");

        } else if (confirmar == 'N' || confirmar == 'n'){

            System.out.println("Compra cancel·lada");

        }
    }
}
