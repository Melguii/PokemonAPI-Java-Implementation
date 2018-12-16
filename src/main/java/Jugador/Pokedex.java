package Jugador;

import Pokemon.Pokemon;

import java.util.ArrayList;

public class Pokedex {
    private ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();

    public Pokemon getPokemonById(int id) {
        for (Pokemon i : pokedex) {
            if (i.getId() == id){
                return i;
            }
        }
        return null;
    }

    public void printPokedex(){
        for (Pokemon i : pokedex) {
            System.out.println(i);
        }
    }

    public void setPokedex(ArrayList<Pokemon> pokedex) {
        this.pokedex = pokedex;
    }
}
