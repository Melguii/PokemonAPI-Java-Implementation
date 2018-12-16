package Jugador;

import Pokemon.*;
import Pokemon.Especial.Legendario.Legendario;
import Pokemon.Especial.Mistico.Mitico;
import utils.CheckType;

import java.util.ArrayList;

public class Pokedex {
    private ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();

    public Pokemon getPokemonById(int id) {
        for (Pokemon i : pokedex) {
            if (i.getId() == id){
                return i;
            }
        }
        return null;
    }

    public void printPokedex(){
        for (Pokemon i : pokedex) {
            if(i.getClass() == Salvaje.class){
                Salvaje j = (Salvaje)i;
                System.out.println(j.getId());
                System.out.println(j.getName());
                System.out.println(j.getCapture_rate());
            }
            if (i.getClass() == Legendario.class){
                Legendario j = (Legendario)i;
                System.out.println(j.getId());
                System.out.println(j.getName());
                System.out.println(j.getCapture_rate());
                System.out.println(j.getGym());
            }
            if (i.getClass() == Mitico.class){
                Mitico j = (Mitico)i;
                System.out.println(j.getId());
                System.out.println(j.getName());
                System.out.println(j.getCapture_rate());
                System.out.println(j.getSpecial_Research());
            }
        }
    }

    public void setPokedex(ArrayList<Pokemon> pokedex) {
        this.pokedex = pokedex;
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
        if (pokemonBuscado.getClass() == Legendario.class){

            System.out.println("Ho sentim, però aquest Pokémon és Llegendari i no apareix salvatge.");
            return null;

        } else if (pokemonBuscado.getClass() == Mitico.class) {

            System.out.println("Ho sentim, però aquest Pokémon és mític i no apareix salvatge.");
            return null;

        } else if (pokemonBuscado == null){
            System.out.println("Ho sentim, però aquest Pokémon no existeix (encara).");
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
}
