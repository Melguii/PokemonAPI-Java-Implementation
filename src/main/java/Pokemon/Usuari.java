package Pokemon;

import java.util.Scanner;

public class Usuari {
    int monedas;            //Empieza con 1000 monedas inciales

    //Constructor Usuario
    public Usuari(){
        monedas = 1000;
    }

    /**
     * Funcion llamada por el menu, donde el usuario pide al programa comprar monedas
     */
    public void comprarMonedas(){
        int monedas;
        double precio;
        char confirmar;

        //Pedimos al usuario cuantas monedas quiere comprar
        System.out.println("Quantes monedes vol comprar?");
        Scanner scMonedas = new Scanner (System.in);
        monedas = scMonedas.nextInt();

        //Controlamos en cual de los intervarlos esta el valor de las monedas y mostramos su precio
        //al usuario
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
        System.out.println("El preu total és de" + precio + "€. Confirma la compra? (Y/N)");
        Scanner scConfirmar = new Scanner (System.in);
        confirmar = scConfirmar.nextChar();

        if (confirmar.equals(Y) || confirmar.equals(y)){

            System.out.println("S'han afegit" + monedas + "monedes al seu compte");

        } else {

            System.out.println("Compra cancel·lada");

        }
    }

}
