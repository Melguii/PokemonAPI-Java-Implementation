package Jugador;

import Pokemon.*;
import Pokemon.Especial.Legendario.Legendario;
import Pokemon.Especial.Mistico.Mitico;

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
}
