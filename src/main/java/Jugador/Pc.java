package Jugador;

import Pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Pc {
    private List<Pokemon> pc = new ArrayList<Pokemon>();    //Array de todos los pokémon capturados

    /**
     * Función que guarda el pokémon al Pc. (Sitio donde el usuario puede guardar sus pokémon)
     * @param pokemonToPc: el pokemon que quiere guardar
     */
    public void setPokemonToPc(Pokemon pokemonToPc) {
        pc.add(pokemonToPc);
    }

    /**
     * Devuelve la lista de todos los pokémon guardados en el Pc
     * @return todos los pokémon guardados
     */
    public List<Pokemon> getPokemonsCapturados() {
        return pc;
    }
}
