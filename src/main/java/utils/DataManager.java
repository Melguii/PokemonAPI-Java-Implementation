package utils;

import Jugador.*;
import Pokemon.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataManager {
    //Constantes
    private static final String PATH = "files/";
    private static final String FILE1 = "balls.json";
    private static final String FILE2 = "poke.json";
    private static final String FILE3 = "legends.json";
    private static final String separator = System.lineSeparator();

    //Atributos de la clase
    private Tienda tienda = new Tienda();
    private Pokedex pokedex = new Pokedex();
    private Usuario usuario;

    //Getters

    /**
     * Carga la infomacion del Json de pokebolls en la tienda
     */
    public void loadDataPokeballs() {
        Gson gson = new Gson();
        JsonReader reader;
        Pokeball[] pokeballs;
        try {
            reader = new JsonReader(new FileReader(PATH + FILE1));
            pokeballs = gson.fromJson(reader, Pokeball[].class);
        } catch (FileNotFoundException e) {
            pokeballs = null;
            e.printStackTrace();
        }
        this.tienda.setPokeballs(pokeballs);
    }

    /**
     * Carga la informacion del usuario
     */
    public void loadUser() {
        usuario = new Usuario(tienda.getFirstPokeball());
    }


    /**
     * Ahora cargamos la informacion de los todos los pokemons.
     */
    public void loadDataPokedex() {
        Pokemon[] pokemonsAux = loadPokemon(FILE2);
        ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>(Arrays.asList(pokemonsAux));
        pokemonsAux = loadPokemon(FILE3);
        int i = 0;
        for (Pokemon pokemonActual : pokedex) {
            for (Pokemon pokemonAuxActual : pokemonsAux) {
                if (pokemonActual.getId() == pokemonAuxActual.getId()) {
                    pokemonAuxActual.setName(pokemonActual.getName());
                    pokemonAuxActual.setCapture_rate(pokemonActual.getCapture_rate());
                    pokedex.set(i, pokemonAuxActual);
                }
            }
            i++;
        }
        this.pokedex.setPokedex(pokedex);
    }


    //Metodos

    /**
     * Lee la informacion de los pokemons
     *
     * @param file: El archivo a leer
     * @return Array de pokemons
     */
    private Pokemon[] loadPokemon(String file) {
        Pokemon[] pokemons;
        JsonReader reader;
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(Pokemon.class, new IdDeserializer()).create();
            reader = new JsonReader(new FileReader(PATH + file));
            pokemons = gson.fromJson(reader, Pokemon[].class);
        } catch (FileNotFoundException e) {
            pokemons = null;
            e.printStackTrace();
        }
        return pokemons;
    }


    /**
     * Maneja los datos
     * @param opcion
     */
    public void seleccionarOpcio(int opcion) {
        switch (opcion) {
            case 1:
                //Monedas
                usuario.comprarMonedas();
                break;

            case 2:
                //Comprar Objetos

                System.out.println("Teniu " + usuario.getMonedas() +  " monedes.\n");
                tienda.mostrarObjetos();
                char eleccion = usuario.pideObjeto();
                List<Pokeball> pokeball = tienda.getObjetos(eleccion);
                usuario.addItemsInventario(pokeball);

                break;

            case 3:

                //Consultar Inventario
                usuario.consultarInventario();

                break;

            case 4:

                //Capturar pokemons salvajes
                boolean disponibilidad = usuario.pokeballsDisponibles();

                if (disponibilidad){

                    String parametro = usuario.peticionPokemon();
                    SistemaCaptura sistemaCaptura = new SistemaCaptura();
                    Pokemon pokemon = pokedex.buscarPokemonSalvaje(parametro);

                    if (pokemon.getName() != null){
                        usuario.
                        SistemaCaptura captura = new SistemaCaptura();
                        captura.sistemaCaptura(inventario, pokemon);
                    }


                } else {
                    System.out.println("Ho sentim, però no té Pokéballs disponibles, pel que no pot buscar Pokémons.");
                }


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
                System.out.println("Ens veiem!");

                break;

            case 10:
                //Si queremos hacer el sistema de partidas:
                //      Deberíamos colocar un sitema que controle si el usuario quiere guardar o no la partida

                //En caso que hagamos partidas ya creadas:
                //System.out.println("Guardant Partida");
                //wait(1);
                //System.out.print(".");
                //wait(1);
                //System.out.print(".");
                //wait(1);
                //System.out.println(".");
                //wait(2);
                //System.out.println("Fet!\nTorna quan vulguis!");
                break;

        }
    }

}