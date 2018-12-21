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

    public Pokemon getPokemonById(int id) {
        for (Pokemon i : this.pokedex) {
            if (i.getId() == id){
                return i;
            }
        }
        return null;
    }

    public ArrayList<Pokemon> getPokedex() {
        return pokedex;
    }


    public void setPokedex(ArrayList<Pokemon> pokedex2) {
        this.pokedex.addAll(pokedex2);
    }

    public Pokemon buscarPokemonSalvaje(String parametro){
        int idBuscado;
        Pokemon pokemonBuscado;
        CheckType ch = new CheckType();

        boolean numeric = ch.checkType(parametro);

        if (numeric){
            idBuscado = Integer.parseInt(parametro);
            pokemonBuscado = buscarPokemonPorId(idBuscado);
        } else {
            pokemonBuscado = buscarPokemonPorNombre(parametro);
        }

        //Controlamos que no sea un pokemon legenadario ni mitico
        try {
            if (pokemonBuscado.getClass() == Legendario.class){

                System.out.println("Ho sentim, però aquest Pokémon és Llegendari i no apareix salvatge.\n");
                return null;

            } else if (pokemonBuscado.getClass() == Mitico.class) {

                System.out.println("Ho sentim, però aquest Pokémon és mític i no apareix salvatge.\n");
                return null;
            }
        }catch (NullPointerException e){

            System.out.println("Ho sentim, però aquest Pokémon no existeix (encara).\n");
            return null;

        }

        return pokemonBuscado;
    }

    public Pokemon buscarPokemonPorId(int id){
        for (Pokemon pokemon : pokedex) {
            if (pokemon.getId() == id){

                return pokemon;
            }
        }

        return null;
    }

    public Pokemon buscarPokemonPorNombre(String nombre){
        nombre = nombre.toLowerCase();

        for (Pokemon pokemon : pokedex) {
            if (pokemon.getName().equals(nombre)){

                return pokemon;
            }
        }

        return null;
    }

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

    public double getCaptureRatePokemon(String name) {
        for (Pokemon pokemon: pokedex) {
            if (pokemon.getName().equals(name)){
                return pokemon.getCapture_rate();
            }
        }
        return 0;
    }

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

    public void setCompletedResearch(int id) {
        for (Pokemon i : pokedex) {
            if (i.getId() == id && i instanceof  Mitico){
                ((Mitico) i).SpecialResearchCompleted();
            }
        }
    }

    public void resetSpecialResearch(int id) {
        for (Pokemon i : pokedex) {
            if (i.getId() == id && i instanceof  Mitico){
                ((Mitico) i).ResetSpecialResearch();
            }
        }
    }

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
