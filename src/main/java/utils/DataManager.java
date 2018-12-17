package utils;

import Jugador.*;
import Pokemon.*;
import Pokemon.Especial.Legendario.Legendario;
import Pokemon.Especial.Mistico.Mitico;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import static java.lang.Math.pow;
import static java.lang.Math.random;

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
                boolean quedanPokeballs = usuario.pokeballsDisponibles();

                if (quedanPokeballs){
                    String parametro = usuario.peticionPokemon();
                    Pokemon pokemon = pokedex.buscarPokemonSalvaje(parametro);

                    if (pokemon != null){
                        boolean capturado = sistemaCaptura(pokemon);

                        if (capturado){
                            usuario.pokemonCapturado(pokemon);
                        }
                    }
                }


                break;

            case 5:

                break;

            case 6:

                break;

            case 7:

                break;

            case 8:

                String idPokemon = usuario.peticionInformacion();
                Pokemon pokemon = pokedex.buscarPokemon(idPokemon);

                if(pokemon != null){
                    PokeApi pokeApi = new PokeApi(pokemon.getId());
                    setMoreInformactionOfPokemon(pokemon,pokeApi);

                    try {

                        WriteFileInformation(pokemon);

                    } catch (IOException e) {

                        e.printStackTrace();

                    }
                }

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

    public boolean sistemaCaptura(Pokemon pokemon){
        int ecuacion;
        boolean capturado = false;

        if (pokemon.getClass() == Legendario.class){
            ecuacion = 2;
            capturado = resultadoCaptura(pokemon, ecuacion);

        } else if (pokemon.getClass() == Mitico.class){
            ecuacion = 3;
            capturado = resultadoCaptura(pokemon, ecuacion);

        } else {
            ecuacion = 1;
            System.out.println("Un " + pokemon.getName() + " salvatge aparegué!");
            capturado = resultadoCaptura(pokemon, ecuacion);

        }

        return capturado;
    }

    public boolean resultadoCaptura(Pokemon pokemon, int ecuacion){
        double pc = 0;                                              //Probabilidad de Captura
        double pb;                                                  //Pokeball Capture Rate
        double pm;                                                  //Pokemon Capture Rate
        boolean atrapado = false;
        boolean tieneTipoPokeball = true;
        int intents = 5;
        String tipoPokeball;
        double random;
        Pokeball[] pokeballsExistentes = tienda.getPokeballs();     //Pokeballs que existen en el juego definidas en la tienda como posibles compras

        do{
            System.out.println("Queden "+ usuario.pokeballsTotales() + " Pokéballs i "+ intents +"/5 intents. Quin tipus de Pokéball vol fer servir?");

            do {
                Scanner scPokeball = new Scanner(System.in);
                tipoPokeball = scPokeball.nextLine();
                tipoPokeball = tipoPokeball.toLowerCase();
                tieneTipoPokeball = usuario.existeEnInventario(tipoPokeball, pokeballsExistentes);

            }while (!tieneTipoPokeball);

            random = Math.random();
            pb = pokeballCaptureRate(tipoPokeball);
            pm = pokemonCaptureRate(pokemon.getName());
            pc = resultadoEcuacion(pb, pm, ecuacion);


            if (pc >= random){
                atrapado = true;

            } else {
                System.out.println("La " + tipoPokeball + " ha fallat!");
                intents--;
                usuario.setTotalPokeballs(usuario.pokeballsTotales() - 1);
            }

        }while (intents > 0 && usuario.pokeballsTotales() > 0 && !atrapado);

        if (intents == 0){
            System.out.println("El Ralts ha escapat...\n");

        } else  if (usuario.pokeballsTotales() == 0) {
            System.out.println("No queden Pokeballs...\n");

        }

        return atrapado;
    }

    public double resultadoEcuacion(double pb, double pm, int ecuacion){
        double pc = 0;

        switch (ecuacion){
            case 1:

                pc = ((pb/256) + (pm/2048));
                break;

            case 2:

                pc = (pow(pb, 1.5) + pow(pm, Math.PI))/4096;
                break;

            case 3:

                pc = ((pb/pb)+(pm/pm))/2;
                break;

        }

        return pc;
    }

    public int pokeballCaptureRate(String nombrePokeball){
        for (Pokeball pokeball: tienda.getPokeballs()) {
            if (pokeball.getName().equals(nombrePokeball)){
                return pokeball.getCapture_rate();
            }
        }

        return 0;
    }

    public int pokemonCaptureRate(String nombrePokemon){
        for (Pokemon pokemon: pokedex.getPokedex()) {
            if (pokemon.getName().equals(nombrePokemon)){
                return pokemon.getCapture_rate();
            }
        }

        return 0;
    }


    private void WriteFileInformation(Pokemon pokemon) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(pokemon.getName() + ".html"));
        writer.write("<html>");
        writer.write("<head>");
        writer.write("<title>" + pokemon.getName() + "</title>");
        writer.write("<head>");
        writer.write("</html>");
        writer.write("<body>");
        writer.write("<h1>" + pokemon.getName() + "(" + pokemon.getId() + ")" + "</h1>");
        writer.write("<img src=\"" + pokemon.getFront_default() + "\"style=\"width:500px;height:500px;\">");
        writer.write("<p>" + pokemon.getFlavor_text() +"</p>");
        writer.write("</body>");
        writer.close();
    }

    private void setMoreInformactionOfPokemon(Pokemon pokemon, PokeApi pokeApi) {
        pokemon.setHeight(pokeApi.getHeight());
        pokemon.setWeight(pokeApi.getWeight());
        pokemon.setFront_default(pokeApi.getDefaultSprite());
        pokemon.setFlavor_text(pokeApi.getFlavorTextEnglish());
        pokemon.setBase_experience(pokeApi.getBaseExperience());
    }

}