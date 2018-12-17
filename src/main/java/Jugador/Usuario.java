package Jugador;

import java.util.List;
import java.util.Scanner;

import Pokemon.Pokemon;

public class Usuario {

    //Atributos de la clase
    private int monedas;
    private Inventario inventario;

    //Constructor
    public Usuario(Pokeball firstPokeball) {
        this.monedas = 1000;
        this.inventario = new Inventario(firstPokeball);
    }

    //Getters and Setters

    /**
     * Getter monenas
     * @return monedas
     */
    public int getMonedas() {
        return monedas;
    }


    /**
     * Funcion llamada por el menu, donde el usuario pide al programa comprar monedas (Revisar)
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
            System.out.println("El preu total és de " + precio + "€. Confirma la compra? (Y/N)");
            Scanner scConfirmar = new Scanner(System.in);
            confirmar = scConfirmar.next().charAt(0);

            //En caso de introducir datos no correctos volvemos a pedirlos hasta que el usuario
            //introduzca una opción correcta
            if (confirmar != 'Y' && confirmar != 'y' && confirmar != 'N' && confirmar != 'n') {
                System.out.println("Error, opció no correcte.");
            }

        } while (confirmar != 'Y' && confirmar != 'y' && confirmar != 'N' && confirmar != 'n');

        if (confirmar == 'Y' || confirmar == 'y') {
            this.monedas += monedas;
            System.out.println(this.monedas);
            System.out.println("S'han afegit " + monedas + " monedes al seu compte");

        } else {

            System.out.println("Compra cancel·lada");

        }
    }


    /**
     * El usuario pide un objeto
     * @return char del objeto a seleccionar
     */
    public char pideObjeto() {
        System.out.println("Esculli una opció:");
        Scanner sc = new Scanner(System.in);
        return Character.toUpperCase(sc.next().charAt(0));
    }

    /**
     * Insertar objetos al inventario
     * @param pokeballs
     */
    public void addItemsInventario(List<Pokeball> pokeballs) {
        if (pokeballs != null) {
            int price = pokeballs.size() * pokeballs.get(0).getPrice();

            if (price > this.monedas) {
                System.out.println("Ho sentim, però no disposa de suficients monedes.");
            } else {
                this.monedas -= price;
                inventario.addPokeballs(pokeballs);
                setTotalPokeballs(pokeballsTotales() + pokeballs.size());
                System.out.println("S'han afegit " + pokeballs.size() + " " +  pokeballs.get(0).getName() + " al seu compte a canvi de "  + price + " monedes.\n");
            }
        }
    }

    /**
     * Funcion que llama a la consulta del inventario
     */
    public void consultarInventario(){
        inventario.consultarInventario();
    }

    /**
     * Pedimos al usuario que introduzca el pokemon que está buscando
     * @return devuelve el ie o
     */
    public String peticionPokemon(){

            System.out.println("Quin Pokémon vol buscar?");
            Scanner sc = new Scanner(System.in);
            return sc.nextLine();

    }

    public String peticionInformacion(){
        System.out.println("De quin Pokémon vol informació?");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Funcion encargada de añadir el pokemon capturado al pc
     * @param pokemon
     */
    public void pokemonCapturado(Pokemon pokemon){

    }

    /**
     * Comprovamos que le queden pokeballs al usuario
     * @return si queden pokeballs disponibles
     */
    public boolean pokeballsDisponibles(){
        if (inventario.getTotalPokeballs() == 0){
            System.out.println("Ho sentim, però no té Pokéballs disponibles, pel que no pot buscar Pokémons.");
            return false;

        } else {
            return true;

        }
    }

    /**
     * Obtenemos cuantas pokeballs le quedan al usuario
     * @return
     */
    public int pokeballsTotales(){
        return inventario.getTotalPokeballs();
    }

    /**
     * Establezemos el nuevo total de pokeballs restantes del usuario
     * @param pokeballs
     */
    public void setTotalPokeballs(int pokeballs){
        inventario.setTotalPokeballs(pokeballs);
    }

}

