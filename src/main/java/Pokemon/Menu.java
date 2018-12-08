package com.Pokemon;

import java.util.Scanner;

public class Menu {
    int opcio;

    public void menu (){
        do {
            mostrarMenu();
            opcio = opcioRequest();
            seleccionarOpcio(opcio);

        }while (opcio != 10);
    }

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

    private int opcioRequest(){
        System.out.println("Introdueix opcio: ");
        Scanner sc = new Scanner (System.in);
        return sc.nextInt();
    }

    private void seleccionarOpcio(int opcio) {
        switch (opcio){
            case 1:

                break;

            case 2:

                break;

            case 3:

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
                System.out.println("Ens veiem!");
                break;

            default:
                System.out.println("Error, opcio no correcte");

                break;
        }
    }
}
