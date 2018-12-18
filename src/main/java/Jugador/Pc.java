package Jugador;

import Pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Pc {
    private List<Pokemon> pc = new ArrayList<Pokemon>();

    public void setPokemonToPc(Pokemon pokemonToPc) {
        pc.add(pokemonToPc);
    }

    public List<Pokemon> getPokemonsCapturados() {
        return pc;
    }
}
