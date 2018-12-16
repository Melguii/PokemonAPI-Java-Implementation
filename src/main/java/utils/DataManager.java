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

public class DataManager {
    private static final String PATH = "files/";
    private static final String FILE1 = "balls.json";
    private static final String FILE2 = "poke.json";
    private static final String FILE3 = "legends.json";
    private static final String separator = System.lineSeparator();

    //INFORMATION THAT WE READ

    private Pokeball[] pokeballs;
    private ArrayList<Pokemon> pokemons;



    public void loadData(){
        Gson gson = new Gson();
        JsonReader reader;
        Pokemon[] pokemonsAux;
        try {
            reader = new JsonReader(new FileReader(PATH + FILE1));
            pokeballs = gson.fromJson(reader, Pokeball[].class);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pokemonsAux = loadPokemon(FILE2);
        this.pokemons = new ArrayList<Pokemon>(Arrays.asList(pokemonsAux));
        pokemonsAux = loadPokemon(FILE3);
        //Recorrer y ver los que son iguales :) y ya estaria
        int n = this.pokemons.size();
        int w = pokemonsAux.length;
        this.pokemons.addAll(Arrays.asList(pokemonsAux));
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

    public void print() {
        for (Pokemon i: pokemons) {
            System.out.println(i);
        }
    }
}
