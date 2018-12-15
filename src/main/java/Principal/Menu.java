package Principal;

import java.util.Scanner;

public class Menu {
    private int opcio;

    /**
     * Funcion que controla es sistema de menu, sitema de opciones y opcion de salida
     */
    public void menu (){
        do {
            mostrarMenu();
            opcio = opcioRequest();
            seleccionarOpcio(opcio);

        }while (opcio != 10);
    }

    /**
     * Funcion que contiene los mensajes del propio menu
     */
    private void mostrarMenu() {
        System.out.println("1. Afegir Monedes");
        System.out.println("2. Comprar Objectes");
        System.out.println("3. Consultar Inventari");
        System.out.println("4. Buscar Pokémon Salvatge");
        System.out.println("5. Fer Raid");
        System.out.println("6. Recerques Especials Actuals");
        System.out.println("7. Informe de Captures");
        System.out.println("8. Informació Detallada");
        System.out.println("9. Cargar Partida Guardada");
    }

    /**
     * Funcion que pide al usuario introducir una opcion
     * @return sc.nextInt(): La opcion escrita por el usuario
     */
    private int opcioRequest(){
        System.out.println("Introdueix opcio: ");
        Scanner sc = new Scanner (System.in);
        return sc.nextInt();
    }

    /**
     * Esta funcion ejectua la opcion que quiera el usuario del menu
     * @param opcio Consta del parametro con el valor del menu que quier el usuario
     */
    private void seleccionarOpcio(int opcio) {
        switch (opcio){
            case 1:
                //Monedas

                break;

            case 2:
                //Comprar Objetos

                break;

            case 3:
                //Consultar Inventario

                break;

            case 4:

                break;

            case 5:

                break;

            case 6:

                break;

            case 7:

                break;

            case 8:

                break;

            case 9:

                break;

            case 10:
                //Si queremos hacer el sistema de partidas:
                //      Deberíamos colocar un sitema que controle si el usuario quiere guardar o no la partida
                System.out.println("Ens veiem!");

                //En caso que hagamos partidas ya creadas:
                //System.out.println("Guardant Partida");
                //wait(1);
                //System.out.print(".");
                //wait(1);
                //System.out.print(".");
                //wait(1);
                //System.out.println(".");
                //wait(2);
                //System.out.println("Fet!\nTorna quan vulguis!");
                break;

            default:
                System.out.println("Error, opcio no correcte");

                break;
        }
    }
}
