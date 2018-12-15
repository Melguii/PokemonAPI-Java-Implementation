package Jugador;

import java.util.ArrayList;
import java.util.List;

import Jugador.Pokeball;

public class Inventario {
    int totalPokeballs;
    List<Pokeball> pokeballs = new ArrayList<Pokeball>();

    public Inventario(){
        //empezamos con 3 pokeballs

    }

    public void listaPokeballs(){

    }

    public  List<Pokeball> getPokeballs(){
        return pokeballs;
    }

    public void setPokeballs( List<Pokeball> pokeballs){
        this.pokeballs = pokeballs;
    }


}
