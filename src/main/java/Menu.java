import java.util.Scanner;


public class Menu {
    private int opcion;
    private Scanner sc;

    public Menu(){
        this.sc = new Scanner (System.in);
        opcion = -1;
    }

    /**
     * Funcion que contiene los mensajes del propio menu
     */
    public void mostrarMenu() {
        System.out.println("1. Afegir Monedes");
        System.out.println("2. Comprar Objectes");
        System.out.println("3. Consultar Inventari");
        System.out.println("4. Buscar Pokémon Salvatge");
        System.out.println("5. Fer Raid");
        System.out.println("6. Recerques Especials Actuals");
        System.out.println("7. Informe de Captures");
        System.out.println("8. Informació Detallada");
        System.out.println("9. Sortir");
    }

    /**
     * Funcion que pide al usuario introducir una opcion
     * @return sc.nextInt(): La opcion escrita por el usuario
     */
    public void peticion(){
        System.out.println("Introdueix opcio: ");
        this.opcion =  sc.nextInt();
    }


    public boolean opcionValida(){
        if ( !(opcion >= 1 && opcion <= 9)){
            System.out.println("Error, opció incorrecte");
        }
        return opcion >= 1 && opcion <= 9;
    }

    public boolean salir() {
        return opcion == 9;
    }

    public int getOpcion() {
        return this.opcion;
    }
}
