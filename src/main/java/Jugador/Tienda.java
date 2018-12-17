package Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tienda {
    private Pokeball[] pokeballs;

    public Pokeball[] getPokeballs() {
        return pokeballs;
    }

    public void setPokeballs(Pokeball[] pokeballs) {
        this.pokeballs = pokeballs;
    }

    public void mostrarObjetos() {
        char i = 'a';
        System.out.println("Pokéballs disponibles:");

        for (Pokeball pokeActual: pokeballs) {
            System.out.println(i++ + ") " + pokeActual.getName() + ":\t" + pokeActual.getPrice() + " monedes");
        }
        System.out.println("\n"+ i + ") " + "Sortir sense comprar\n");
    }

    public List<Pokeball> getObjetos(char eleccion) {
        List<Pokeball> pokeballs = new ArrayList<Pokeball>();
        int index = eleccion - 'A';
        if (index > this.pokeballs.length-1 || index < 0){
            return  null;
        }else{
            System.out.println("Quantes unitats en vol comprar?");
            Scanner sc = new Scanner(System.in);
            int cuantas = sc.nextInt();

            if(cuantas < 1){

                return null;

            }else{
                for (int i = 0; i < cuantas; i++) {
                    pokeballs.add(this.pokeballs[index]);
                }
                return pokeballs;
            }
        }
    }

    public Pokeball getFirstPokeball() {
        return pokeballs[0];
    }
}
