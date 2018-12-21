package Jugador;

import Pokemon.*;
import Pokemon.Especial.Legendario.Legendario;
import Pokemon.Especial.Legendario.Location;
import Pokemon.Especial.Mistico.Mitico;
import Pokemon.Especial.Mistico.Quest;
import Pokemon.Especial.Mistico.SpecialResearch;
import Utils.CheckType;
import Utils.Haversine;


import java.util.ArrayList;

public class Pokedex {
    private ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();

    /**
     * Muestra la recerques que el usuario esta realizando en este momento.
     */
    public void showEspecialRecerques() {

        System.out.println("Recerques Especials:\n");

        for (Pokemon i: pokedex) {
            if(i instanceof Mitico){
                Mitico mitico = (Mitico)i;

                if (mitico.getEnCurso()){
                     SpecialResearch sp =  mitico.getSpecial_Research();
                     ArrayList<Quest> quests = sp.getQuests();

                    for (Quest q: quests) {
                        int id = q.getTarget();
                        String name = getPokemonById(id).getName();
                        q.printQuest(name);

                    }
                }
            }
        }
    }

    /**
     * Devuele un pokemon segun una id
     * @param id
     * @return el pokemon a buscar
     */
    public Pokemon getPokemonById(int id) {
        for (Pokemon i : this.pokedex) {
            if (i.getId() == id){
                return i;
            }
        }
        return null;
    }

    /**
     * Devuelve toda la pokédex, es decir, todos los pokémon registrados con tdoa su información
     */
    public ArrayList<Pokemon> getPokedex() {
        return pokedex;
    }

    /**
     * Añade pokemons a la pokedex
     * @param pokedex2
     */
    public void setPokedex(ArrayList<Pokemon> pokedex2) {
        this.pokedex.addAll(pokedex2);
    }

    /**
     * Método que permite buscar por nombre o id un pokémon salvaje, es decir, que no puede ser ni legendario ni mítico.
     * Si el pokémon que se le pasa por parámetro es como bien se ha esmentado anteriormente un pokemon legendario o
     * mítico, éste mismo método avisará al usuario que el pokémon buscado no puede ser ninguno de los dos tipos ya esmetados.
     * Sino hay ningúm problema, y encuentra el pokémon, devolverá el pokémon con toda su información.
     *
     * @param parametro: nombre o id del pokémon buscado.
     * @return devuelve el pokémon y toda su información
     */
    public Pokemon buscarPokemonSalvaje(String parametro){
        int idBuscado;
        Pokemon pokemonBuscado;
        CheckType ch = new CheckType();

        /* Comprovamos si el valor que se le pasa a la función es el id o nombre mediante su tipo.*/
        boolean numeric = ch.checkType(parametro);

        /*Dependiendo si es id o nombre usaremos una busqueda u otra*/
        if (numeric){
            idBuscado = Integer.parseInt(parametro);
            pokemonBuscado = buscarPokemonPorId(idBuscado);
        } else {
            pokemonBuscado = buscarPokemonPorNombre(parametro);
        }

        /*Controlamos que no sea un pokemon legenadario ni mitico*/
        try {
            if (pokemonBuscado.getClass() == Legendario.class){

                System.out.println("Ho sentim, però aquest Pokémon és Llegendari i no apareix salvatge.\n");      //Como este método solo busca pokémon salvajes si encuentra un pokémon legendario
                return null;                                                                                      // y no uno de salvaje informa por pantalla

            } else if (pokemonBuscado.getClass() == Mitico.class) {

                System.out.println("Ho sentim, però aquest Pokémon és mític i no apareix salvatge.\n");           //Como este método solo busca pokémon salvajes si encuentra un pokémon mítico
                return null;                                                                                      // y no uno de salvaje informa por pantalla
            }
        }catch (NullPointerException e){

            System.out.println("Ho sentim, però aquest Pokémon no existeix (encara).\n");                         //En caso que no encuentre el pokémon y de error, este devuelve un mensaje conforme no lo ha encontrado

            return null;

        }

        return pokemonBuscado;
    }

    /**
     * Método que compara el id que se le pasa por parámetro con cada uno de los Pokémon. En el momento que un id coincide
     * devuelve toda la información del pokémon que ha coincidido
     *
     * @param id: id int del pokémon
     * @return devuelve toda la información del pokémon registrado en la pokédex que coincide con la del id pasado por
     * parámetro
     */
    public Pokemon buscarPokemonPorId(int id){
        for (Pokemon pokemon : pokedex) {
            if (pokemon.getId() == id){

                return pokemon;
            }
        }

        return null;
    }

    /**
     * Método que compara el nombre que se le pasa por parámetro con cada uno de los Pokémon. En el momento que un nombre coincide
     * devuelve toda la información del pokémon que ha coincidido
     *
     * @param nombre: nombre String del pokémon
     * @return devuelve toda la información del pokémon registrado en la pokédex que coincide con el nombre pasado por
     * parámetro
     */
    public Pokemon buscarPokemonPorNombre(String nombre){
        nombre = nombre.toLowerCase();

        for (Pokemon pokemon : pokedex) {
            if (pokemon.getName().equals(nombre)){

                return pokemon;
            }
        }

        return null;
    }

    /**
     * Busca un pokemon independiente si es un nombre o una id
     * @param idPokemon
     * @return Pokemon a buscar
     */
    public Pokemon buscarPokemon(String idPokemon) {
        int idBuscado;
        CheckType ch = new CheckType();

        boolean numeric = ch.checkType(idPokemon);

        if (numeric){
            idBuscado = Integer.parseInt(idPokemon);
            return buscarPokemonPorId(idBuscado);

        } else {
            return buscarPokemonPorNombre(idPokemon);
        }
    }

    /**
     * El pokémon de la pokédex que coincida con el nombre del pokémon pasado por parámetro, devuelve su capture_rate
     * @param name: nombre del pokémon buscado
     * @return devuelve el capture_rate del pokémon buscado
     */
    public double getCaptureRatePokemon(String name) {
        for (Pokemon pokemon: pokedex) {
            if (pokemon.getName().equals(name)){
                return pokemon.getCapture_rate();
            }
        }
        return 0;
    }

    /**
     * Comprueba que la Research ha sido completado o no, si ha sido completada devuelve el id del pokemon con el que tiene que luchar
     * @param id
     * @return id del pokemon a luchar, o -1 si no hay que luchar
     */
    public int checkSpecialResearchIsCompleted(int id) {
        boolean fight = false;

        for (Pokemon i: pokedex){
            if (i instanceof Mitico){
                Mitico mitico = (Mitico)i;
                fight = mitico.checkSpecialResearch(id);

            }

            if (fight){
                return i.getId();

            }
        }

        return -1;
    }

    /**
     * Actualiza la informacion de la pokedex para que decir que la research esta completada
     * @param id
     */
    public void setCompletedResearch(int id) {
        for (Pokemon i : pokedex) {
            if (i.getId() == id && i instanceof  Mitico){
                ((Mitico) i).SpecialResearchCompleted();
            }
        }
    }

    /**
     * Restea la SpecialResearch
     * @param id
     */
    public void resetSpecialResearch(int id) {
        for (Pokemon i : pokedex) {
            if (i.getId() == id && i instanceof  Mitico){
                ((Mitico) i).ResetSpecialResearch();
            }
        }
    }

    /**
     * Este método busca el gimnasio más cercano a la posición que el usuario a introducido por parámetro. Para hacerlo realista
     * se usa la clase Haversine que calcula la distáncia como si estuviesemos en la tierra.
     *
     * @param posicionUsuario: posición de latitud y longitud que ha introducido el usuario
     * @return devuelve el pokémon legendario con el gimnasio más cercano a la posición introducida
     */
    public Pokemon buscarGimnasio(Location posicionUsuario){
        Legendario legendario = null;
        float latitud = 0;
        float longitud = 0;
        double distance = 0;
        double minDistance = 6375;

        //Buscamos el pokémon con el gimnasio más cercano al usuario
        for (Pokemon pokemon : pokedex){
            if (pokemon instanceof Legendario){
                Haversine haversine = new Haversine();
                latitud = ((Legendario) pokemon).getGym().getLocation().getLatitude();
                longitud = ((Legendario) pokemon).getGym().getLocation().getLongitude();

                distance = haversine.distanceHarven(posicionUsuario.getLatitude(), posicionUsuario.getLongitude(), latitud, longitud);

                if (distance < minDistance){
                    minDistance = distance;
                    legendario = (Legendario) pokemon;
                }
            }
        }
        if (legendario != null){
            System.out.println("Gimnàs més proper: " + legendario.getGym().getName() +". Començant raid...");
        }

        return legendario;
    }
}
