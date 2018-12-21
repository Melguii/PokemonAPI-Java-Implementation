package Utils;

import Jugador.*;
import Pokemon.*;
import Pokemon.Especial.Legendario.Legendario;
import Pokemon.Especial.Legendario.Location;
import Pokemon.Especial.Mistico.Mitico;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.*;

import java.util.Scanner;

public class DataManager {
    //Constantes
    private static final String PATH = "files/";
    private static final String FILE1 = "balls.json";
    private static final String FILE2 = "poke.json";
    private static final String FILE3 = "legends.json";

    //Atributos de la clase
    private Tienda tienda = new Tienda();
    private Usuario usuario;

    //Getters

    /**
     * Carga la infomación del Json de pokebolls en la tienda
     */
    public void loadDataPokeballs() {
        Gson gson = new Gson();
        JsonReader reader;
        Pokeball[] pokeballs;

        /*Controlamos que el archivo existe en la carpeta que accedemos*/
        try {
            reader = new JsonReader(new FileReader(PATH + FILE1));
            pokeballs = gson.fromJson(reader, Pokeball[].class);
            this.tienda.setPokeballs(pokeballs);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga la información del usuario
     */
    public void loadUser() {
        usuario = new Usuario(tienda.getFirstPokeball());
    }


    /**
     * Ahora cargamos la información de los todos los pokemón.
     */
    public void loadDataPokedex() {
        Pokemon[] pokemonsAux = loadPokemon(FILE2);
        ArrayList<Pokemon> pokedex = new ArrayList<>(Arrays.asList(pokemonsAux));
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
        usuario.setPokedex(pokedex);
    }


    //Metodos

    /**
     * Lee la información de los pokémon
     *
     * @param file: El archivo a leer
     * @return Array de pokémon
     */
    private Pokemon[] loadPokemon(String file) {
        Pokemon[] pokemons;
        JsonReader reader;

        /*Controlamos que el archivo existe en la carpeta que accedemos*/
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
     * Método con cada uno de los métodos principales para cada opción
     * @param opcion la opción que se quiere ejecutar
     */
    public void seleccionarOpcio(int opcion) {

        switch (opcion) {

            /* Opción que permite comprar monedas al Usuario */
            case 1:

                usuario.comprarMonedas();
                break;

            /* Opción que permite comprar objetos al Usuario */
            case 2:

                System.out.println("Teniu " + usuario.getMonedas() +  " monedes.\n");
                tienda.mostrarObjetos();

                char eleccion = usuario.pideObjeto();
                List<Pokeball> pokeball = tienda.getObjetos(eleccion);
                usuario.addItemsInventario(pokeball);

                break;

            /* Opción que permite consultar el inventario del Usuario */
            case 3:

                usuario.consultarInventario();

                break;

            /* Opción que permite capturar un pokémon salvaje. Si se completa una misión, aparecerá en consecuéncia el pokémon
             * mítico que coincida con esa misión completada */
            case 4:
                boolean miticalIsOn = false;
                int miticalId = -1;
                boolean quedanPokeballs;

                do {
                    if (!miticalIsOn){
                        //Capturar pokemons salvajes
                        quedanPokeballs = usuario.pokeballsDisponibles();

                        if (quedanPokeballs){
                            Pokemon pokemon = usuario.peticionPokemon();

                            if (pokemon != null){
                                boolean capturado = sistemaCaptura(pokemon);

                                if (capturado){
                                    usuario.pokemonCapturado(pokemon);
                                    miticalId = usuario.checkSpecialResearchIsCompleted(pokemon.getId());

                                    if (miticalId != -1){
                                        miticalIsOn = true;
                                    }
                                }
                            }
                        }
                    }else{
                        Pokemon pokemon = usuario.getPokemonById(miticalId);
                        System.out.println("Recerca Especial completada: Se t'apareix el mític " + pokemon.getName() + "!");
                        boolean capturado = sistemaCaptura(pokemon);
                        if (capturado){
                            usuario.pokemonCapturado(pokemon);
                            usuario.setCompletedResearch(pokemon.getId());
                            miticalId = usuario.checkSpecialResearchIsCompleted(pokemon.getId());
                            if (miticalId != -1){
                                miticalIsOn = true;
                            }else{
                                miticalIsOn = false;
                            }
                        }else{
                            usuario.resetSpecialResearch(pokemon.getId());
                        }
                    }

                }while (miticalIsOn);

                break;

            /* Opción que permite hacer una Raid, es decir, capturar el pokémon Legendario más cercano a la posición que
             * ha introducido el Usuario */
            case 5:
                Location location = new Location();

                float[] haversine = usuario.peticionHaversine();

                location.setLatitude(haversine[0]);
                location.setLongitude(haversine[1]);

                Pokemon legendario = usuario.getPokemonLegendario(location);

                System.out.println("El boss de raid "+ legendario.getName() +" us repta!");

                boolean capturado = sistemaCaptura(legendario);

                if (capturado){
                    usuario.pokemonCapturado(legendario);
                }

                break;

            /* Opción  que permite al Usuario si tiene algun misión activa y por que porcentaje va */
            case 6:
                usuario.getEspecialRecerques();
                break;

            /* Opción que permite al Usuario consultar todos los pokémon que ha capturado y cuantos de cada uno */
            case 7:
                List<Pokemon> pokemonsCapturados = usuario.getPokemonsCapturados();
                for (Pokemon i :pokemonsCapturados) {
                    setMoreInformactionOfPokemon(i);
                }
                try {
                    writeFileCapturados(pokemonsCapturados);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            /* Opción que permite al Usuario consultar la información detallada de cualquier pokémon */
            case 8:

                Pokemon pokemon = usuario.peticionInformacionPokemon();
                if(pokemon != null){

                    setMoreInformactionOfPokemon(pokemon);
                    try {

                        writeFileInformation(pokemon);

                    } catch (IOException e) {

                        e.printStackTrace();

                    }
                }else{
                    System.out.println("Ho sentim però aquest pokémon no existeix (encara)");
                }

                break;

            /* Opción que permite al Usuario salir del juego */
            case 9:
                System.out.println("Ens veiem!");

                break;

        }
    }

    /**
     * ????????????????????????????????????
     * @param pokemonsCapturados
     * @throws IOException
     */
    private void writeFileCapturados(List<Pokemon> pokemonsCapturados) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter( "htmls/Pokemons_capturats.html"));
        int nPokemons = pokemonsCapturados.size();
        String backgorund = getBackgroundColor();
        writer.write("<html>\n" +
                "\n" +
                "<head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <title>Pokemons capturats: "+nPokemons+"</title>\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Open+Sans\" rel=\"stylesheet\">\n" +
                "\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            "+backgorund+";\n" +
                "            font-family: 'Open Sans', sans-serif;\n" +
                "            max-width: 90%;\n" +
                "            margin: 0 auto;\n" +
                "            margin-top: 2%;\n" +
                "        }\n" +
                "\n" +
                "        .grid-container {\n" +
                "            display: grid;\n" +
                "            grid-template-columns: auto auto auto auto auto auto;\n" +
                "            grid-column-gap: 50px;\n" +
                "            grid-row-gap: 50px;\n" +
                "        }\n" +
                "\n" +
                "        .content {\n" +
                "            border-radius: 25px;\n" +
                "            padding: 10px;\n" +
                "            animation-name: example;\n" +
                "            animation-duration: 4s;\n" +
                "            min-width: 100%;\n" +
                "        }\n" +
                "\n" +
                "        @keyframes example {\n" +
                "            from {\n" +
                "                background-color : white;\n" +
                "            }\n" +
                "\n" +
                "            to {\n" +
                "                background-color: none;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "\n" +
                "    <div>\n" +
                "        <h1 style=\"text-align: center;color : black;\">Pokemons capturats: "+nPokemons+"</h1>\n" +
                "    </div>\n" +
                "    <div class=\"grid-container\">\n" +
                "\n");
        ArrayList<Integer> visitados = new ArrayList<>();
        for (Pokemon i : pokemonsCapturados) {
            if (!visitados.contains(i.getId())){
                visitados.add(i.getId());
                int total = 0;
                for (Pokemon j :pokemonsCapturados) {
                    if (i.getId() == j.getId()){
                        total++;
                    }
                }
                String backgroundiv = getBackgroundColor();
                writer.write("<div class=\"content\" style=\""+backgroundiv+"\">\n" +
                        "            <h2 style=\"text-align: center;\">"+i.getName() + " x"+ total+"</h2><img src=\""+ i.getFront_default() +"\"\n" +
                        "                style=\"display: block;width: 70%;margin: auto;\">\n" +
                        "        </div>");
            }
        }
        writer.write("        </div>\n" +
                "    </div>\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>");
        writer.close();
    }

    /**
     * Método que contiene toda la lógica de captura de cualquir tipo de pokémon
     *
     * @param pokemon el pokémon que se quiere capturar
     * @return si ha capturado al pokémon o no
     */
    private boolean sistemaCaptura(Pokemon pokemon){
        boolean capturado;

        if (pokemon.getClass() == Legendario.class){
            capturado = resultadoCaptura(pokemon);

        } else if (pokemon.getClass() == Mitico.class){
            capturado = resultadoCapturaMi(pokemon);

        } else {
            System.out.println("Un " + pokemon.getName() + " salvatge aparegué!");
            capturado = resultadoCaptura(pokemon);

        }

        return capturado;
    }

    /**
     * ???????????????????????????
     * @param pokemon
     * @return
     */
    private boolean resultadoCapturaMi(Pokemon pokemon){
        boolean pokeballExitente = false;
        boolean atrapado = false;
        int intents = 5;
        String tipoPokeball;
        Pokeball[] pokeballsExistentes = tienda.getPokeballs();
        do {
            System.out.println("Tienes Pokeballs ilimitadas, pero solo " + intents + "  intentos, que pokeball quieres?");
            do {
                Scanner scPokeball = new Scanner(System.in);
                tipoPokeball = scPokeball.nextLine();
                tipoPokeball = tipoPokeball.toLowerCase();
                for (Pokeball pk:pokeballsExistentes) {
                    if (pk.getName().equals(tipoPokeball)){
                        pokeballExitente = true;
                    }
                }
            }while (!pokeballExitente);
            double random = Math.random();
            int pb = pokeballCaptureRate(tipoPokeball);
            double pc = pokemon.captureEcuation(pb);


            if (pc >= random){
                atrapado = true;

            } else {
                System.out.println("La " + tipoPokeball + " ha fallat!");
                intents--;
            }

        }while (intents > 0 && !atrapado);
        return  atrapado;
    }

    /**
     * Este método es el encargado de calcular mediante unas fórmulas una probabilidad de captura que lo comparará con
     * un número random. Si la probabilidad coincide o es más gran que el número random, el pokémon será capturado.
     *
     * @param pokemon: pokémon ha capturar
     * @return si lo ha capturado o no
     */
    private boolean resultadoCaptura(Pokemon pokemon){
        double pc;                                                  //Probabilidad de Captura
        double pb;                                                  //Pokeball Capture Rate
        boolean atrapado = false;
        boolean tieneTipoPokeball;
        int intents = 5;
        String tipoPokeball;
        double random;
        Pokeball[] pokeballsExistentes = tienda.getPokeballs();     //Pokeballs que existen en el juego definidas en la tienda como posibles compras

        do{
            System.out.println("Queden "+ usuario.pokeballsTotales() + " Pokéballs i "+ intents +"/5 intents. Quin tipus de Pokéball vol fer servir?");

            /*Pedimos la pokeball a lanzar y comprovamos que exista*/
            do {
                Scanner scPokeball = new Scanner(System.in);
                tipoPokeball = scPokeball.nextLine();
                tipoPokeball = tipoPokeball.toLowerCase();
                tieneTipoPokeball = usuario.existeEnInventario(tipoPokeball, pokeballsExistentes);

            }while (!tieneTipoPokeball);

            random = Math.random();
            pb = pokeballCaptureRate(tipoPokeball);
            pc = pokemon.captureEcuation(pb);

            /* Una vez calculado todos los valores, comprovamos si la probabilidad de captura es más grande que el número random */
            if (pc >= random){
                atrapado = true;

            } else {
                System.out.println("La " + tipoPokeball + " ha fallat!");
                intents--;
                usuario.setTotalPokeballs(usuario.pokeballsTotales() - 1);
                usuario.usoPokeball(tipoPokeball);
            }

        }while (intents > 0 && usuario.pokeballsTotales() > 0 && !atrapado);

        /* En caso que no le queden intentos o pokeball al usuario, se le informará por pantalla */
        if (intents == 0){
            System.out.println("El " + pokemon.getName() + " escapat...\n");

        } else  if (usuario.pokeballsTotales() == 0) {
            System.out.println("No queden Pokeballs...\n");

        }

        return atrapado;
    }

    /**
     * Con el nombre de la pokeball que se le pasa por parámetro, si coincide con algunas de las pokeball que existen, devolvemos
     * su ratio de captura
     *
     * @param nombrePokeball: nombre de la pokeball que buscamos su capture_rate
     * @return devolvemos el capture_rate de la pokeball
     */
    private int pokeballCaptureRate(String nombrePokeball){
        for (Pokeball pokeball: tienda.getPokeballs()) {
            if (pokeball.getName().equals(nombrePokeball)){
                return pokeball.getCapture_rate();
            }
        }

        return 0;
    }

    /**
     * ??????????????????????
     * @param pokemon
     * @throws IOException
     */
    private void writeFileInformation(Pokemon pokemon) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("htmls/" + pokemon.getName() + ".html"));
        String title = pokemon.getName().toUpperCase();
        String h1 = title  + "(" + pokemon.getId() + ")";
        String description = pokemon.getFlavor_text();
        String img = pokemon.getFront_default();
        String background_div = getBackgroundColor();
        String background_body = getBackgroundColor();
        float weight = pokemon.getWeight()/(float)10;
        int height = pokemon.getHeight();
        int base = pokemon.getBase_experience();
        writer.write("<html>\n" +
                "\n" +
                "<head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <title>" + title +  "</title>\n" +
                "\n" +
                "\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            "+background_body +";\n" +
                "        }\n" +
                "\n" +
                "        .content {\n" +
                "            border-radius: 25px;\n" +
                "            "+background_div +";\n" +
                "            padding: 10px;\n" +
                "            max-width: 800px;\n" +
                "            position: fixed;\n" +
                "            top: 50%;\n" +
                "            left: 50%;\n" +
                "            transform: translate(-50%, -50%);\n" +
                "        }\n" +
                "    </style>\n" +
                "\n" +
                "\n" +
                "    <div class=\"content\">\n" +
                "        <h1 style=\"text-align: center;\">" + h1 + "</h1><img src=\""+ img +"\"\n" +
                "            style=\"display: block;width: 50%;margin: auto;\">\n" +
                "        <div style=\"padding: 20px;\">\n" +
                "            <p>" + description + "</p>\n" +
                "<p style=\"text-align: center;\"> Weight - " + weight + "kg</p>\n"+
                "<p style=\"text-align: center;\"> Height - " + (float)height/(float) 10 + "m</p>\n"+
                "<p style=\"text-align: center;\"> Base Experencie - " + base + "xp</p>\n"+
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>");
        writer.close();
    }

    /**
     * ????????????????????????
     * @return
     */
    private String getBackgroundColor() {
        int color1 = (int) ((Math.random() * ((255 - 180) + 1)) + 180);
        int color2 = (int) ((Math.random() * ((255 - 180) + 1)) + 180);
        int color3 = (int) ((Math.random() * ((255 - 180) + 1)) + 180);
        return  "background-color:rgb(" + color1 + ", " +color2  + ", " + color3  +")";
    }

    /**
     * ????????????????????????????????????????
     * @param pokemon
     */
    private void setMoreInformactionOfPokemon(Pokemon pokemon) {
        if (pokemon.getFlavor_text() == null){
            PokeApi pokeApi = new PokeApi(pokemon.getId());
            pokemon.setHeight(pokeApi.getHeight());
            pokemon.setWeight(pokeApi.getWeight());
            pokemon.setFront_default(pokeApi.getDefaultSprite());
            pokemon.setFlavor_text(pokeApi.getFlavorTextEnglish());
            pokemon.setBase_experience(pokeApi.getBaseExperience());
        }
    }

}