package Jugador;

import java.util.Scanner;

import Jugador.Inventario;

public class Usuario {
    int monedas;            //Empieza con 1000 monedas inciales

    //Constructor Usuario
    public Usuario() {
        monedas = 1000;
    }

    /**
     * Funcion llamada por el menu, donde el usuario pide al programa comprar monedas
     */
    public void comprarMonedas() {
        int monedas;
        double precio = 0;
        char confirmar;

        //Pedimos al usuario cuantas monedas quiere comprar
        do {
            System.out.println("Quantes monedes vol comprar?");
            Scanner scMonedas = new Scanner(System.in);
            monedas = scMonedas.nextInt();

            //Si el usuario mete un valor negadtivo, volvemos a pedir una nueva cantidad
            if (monedas < 0) {
                System.out.println("Cal introduir un nombre estrictament positiu.");
            }

        } while (monedas < 0);

        //Controlamos en cual de los intervarlos esta el valor de las monedas y mostramos su precio
        if (monedas < 250) {
            precio = monedas * 0.01;

        } else if (monedas < 500) {
            precio = monedas * 0.009;

        } else if (monedas < 1000) {
            precio = monedas * 0.007;

        } else if (monedas < 10000) {
            precio = monedas * 0.005;

        } else {
            precio = monedas * 0.0025;

        }

        //Mostramos precio de las monedas en euros y le pedimos al usuario que confirme su compra
        do {
            System.out.println("El preu total és de" + precio + "€. Confirma la compra? (Y/N)");
            Scanner scConfirmar = new Scanner(System.in);
            confirmar = scConfirmar.next().charAt(0);

            //En caso de introducir datos no correctos volvemos a pedirlos hasta que el usuario
            //introduzca una opción correcta
            if (confirmar != 'Y' || confirmar != 'y' || confirmar != 'N' || confirmar != 'n') {
                System.out.println("Error, opció no correcte. Confirma la compra? (Y/N)");
            }

        } while (confirmar != 'Y' || confirmar != 'y' || confirmar != 'N' || confirmar != 'n');

        if (confirmar == 'Y' || confirmar == 'y') {
            this.monedas += monedas;
            System.out.println("S'han afegit" + monedas + "monedes al seu compte");

        } else if (confirmar == 'N' || confirmar == 'n') {

            System.out.println("Compra cancel·lada");

        }
    }
}

    /**
     * Funcion llamada por el menu, donde el usuario accede al menu de comprar de Pokeballs
     */
    /*
    public void comprarObejectos(){
        char opcion;
        int unitats;
        int preu;
        String pokeball;

        System.out.println("Teniu" + monedas + "monedes");

        do {

        }while ();

        menuObjectos();

        if (opcion != 'e'){
            System.out.println("Quantes unitats en vol comprar?");
            Scanner scUnitats = new Scanner (System.in);
            unitats = scUnitats.next().charAt(0);

            if (opcion == 'a'){
                preu = 50*unitats;
                pokeball = "Pokéball";

            } else if (opcion == 'b'){
                preu = 100*unitats;
                pokeball = "Superball";

            } else if (opcion == 'c'){
                preu = 150*unitats;
                pokeball = "Ultraball";

            } else if (opcion == 'd'){
                preu = 1000*unitats;
                pokeball = "Masterball";

            }

            if (monedas >= preu){
                //Actualizamos el total de monedas del usuario y le indicamos el resultado final de la compra
                monedas -= preu;
                System.out.println("S'han afegit" + unitats + pokeball + "al seu compte a canvi de "+ preu +"monedes.");

            } else {
                System.out.println("Ho sentim, però no disposa de suficients monedes");
            }

        } else {
            System.out.println("Ha sortit sense comprar");

        }
    }

    /**
     * Funcion llamada por la funcion "comprarObjetos" que muestra los diferentes productos que se pueden comprar
     */
    /*
    public void menuObjectos(){
        System.out.println("Pokéballs disponibles:\n" +
                "\ta) Pokéball: 50 monedes\n" +
                "\tb) Superball: 100 monedes\n" +
                "\tc) Ultraball: 150 monedes\n" +
                "\td) Masterball: 1000 monedes\n +" +
                "\te) Sortir sense comprar");
    }

    /**
     * Funcion llamada por la funcion "comprarObjetos" que pide al usuario si quiere alguna pokeball en especifico
     * os salir de la tienda
     * @return opcion del menu
     */
    /*
    public char pedirOpcion(){
        char opcion;
        System.out.println("Esculli una opció:\n");
        Scanner scOpcion = new Scanner (System.in);
        opcion = scOpcion.next().charAt(0);

        return opcion;
    }

    /**
     * Funcion con las diferentes pokeballs a escoger
     * @param opcion
     */
    /*
    public void seleccionarObjeto(char opcion){
        switch (opcion){
            case 'a':

                break;
            case 'b':

                break;
            case 'c':

                break;
            case 'd':

                break;
            case 'e':

                break;
            default:

                break;
        }
    }
}
*/
