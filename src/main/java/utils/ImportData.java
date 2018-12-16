package utils;

import Jugador.Pokeball;
import Pokemon.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ImportData {
    private static final String PATH = "files/";
    private static final String FILE1 = "balls.json";
    private static final String FILE2 = "poke.json";
    private static final String FILE3 = "legends.json";
    private static final String separator = System.lineSeparator();


    public Pokeball[] loadDataPokeballs(){
        Gson gson = new Gson();
        JsonReader reader;
        Pokeball[] pokeballs;
        try {
            reader = new JsonReader(new FileReader(PATH + FILE1));
            pokeballs = gson.fromJson(reader, Pokeball[].class);
        }catch (FileNotFoundException e) {
            pokeballs = null;
            e.printStackTrace();
        }
        return pokeballs;
    }

    private Pokemon[] loadPokemon(String file){
        Pokemon[] pokemons;
        JsonReader reader;
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(Pokemon.class,new IdDeserializer()).create();
            reader = new JsonReader(new FileReader(PATH + file));
            pokemons = gson.fromJson(reader, Pokemon[].class);
        }catch (FileNotFoundException e) {
            pokemons = null;
            e.printStackTrace();
        }
        return pokemons;
    }

    public ArrayList<Pokemon> loadDataPokedex() {
        Pokemon[] pokemonsAux = loadPokemon(FILE2);
        ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>(Arrays.asList(pokemonsAux));
        pokemonsAux = loadPokemon(FILE3);
        int i = 0;
        for (Pokemon pokemonActual : pokedex) {
            for (Pokemon pokemonAuxActual : pokemonsAux) {
                if (pokemonActual.getId() == pokemonAuxActual.getId()){
                    pokemonAuxActual.setName(pokemonActual.getName());
                    pokedex.set(i,pokemonAuxActual);
                }
            }
            i++;
        }
        return pokedex;
    }
}
