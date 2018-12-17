package Jugador;

import Pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Pc {
    private List<Pokemon> pc = new ArrayList<Pokemon>();

    public List<Pokemon> getPc() {
        return pc;
    }

    public void setPokemonToPc(Pokemon pokemonToPc) {
        pc.add(pokemonToPc);
    }
}
