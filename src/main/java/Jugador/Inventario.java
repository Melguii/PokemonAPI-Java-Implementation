package Jugador;

import java.util.ArrayList;
import java.util.List;

import Jugador.Pokeball;

public class Inventario {
    //Atributos de la clase
    private List<Pokeball> pokeballs = new ArrayList<Pokeball>();
    private int totalPokeballs;


    //Constructor
    public Inventario(Pokeball firstPokeball) {
        for (int i = 0; i < 3; i++) {
            pokeballs.add(firstPokeball);
        }
        totalPokeballs = pokeballs.size();
    }

    //Getters and setters

    /**
     * Obtiene la lista de Pokeballs
     * @return lista de Pokeballs
     */
    public  List<Pokeball> getPokeballs(){
        return pokeballs;
    }

    /**
     * Inserta pokeballs en la lista
     * @param pokeballs
     */
    public void addPokeballs( List<Pokeball> pokeballs){
        this.pokeballs.addAll(pokeballs);
    }


    public void listaPokeballs(){

    }


}
