package Jugador;

import java.util.ArrayList;
import java.util.List;

import Jugador.Pokeball;

public class Inventario {
    //Atributos de la clase
    private List<Pokeball> pokeballs = new ArrayList<Pokeball>();
    private int totalPokeballs;


    //Constructor
    public Inventario(Pokeball firstPokeball) {
        for (int i = 0; i < 3; i++) {
            pokeballs.add(firstPokeball);
        }
        totalPokeballs = pokeballs.size();
    }

    //Getters and setters

    /**
     * Obtiene la lista de Pokeballs
     * @return lista de Pokeballs
     */
    public  List<Pokeball> getPokeballs(){
        return pokeballs;
    }

    /**
     * Inserta pokeballs en la lista
     * @param pokeballs
     */
    public void addPokeballs( List<Pokeball> pokeballs){
        this.pokeballs.addAll(pokeballs);
    }

    /**
     * Función llamada por el usuario que devuelve la cantidad de cada tipo de pokéball que tiene en el inventario
     */
    public void consultarInventario(){
        List<Pokeball> pokeballsAux = new ArrayList<Pokeball>();
        System.out.println("Inventari:");

        //Antes comprovamos que tenga pokeballs, en caso contrario no mostrara nada
        if  (pokeballs.size() != 0) {
            pokeballsAux.add(pokeballs.get(0));

            //Miramos cuantos tipos de pokeballs diferentes tenemos y añadimos todos
            //ellos que no contenga el array auxiliar
            for (Pokeball pokeball : pokeballs) {
                for (int i = 0; i < pokeballsAux.size(); i++) {
                    if (!pokeballsAux.contains(pokeball)){
                        pokeballsAux.add(pokeball);

                    }
                }
            }


            //Mostramos consecutivamente todos las pokeballs y el total de cada una de ellas
            int total;

            for (Pokeball pokeball : pokeballsAux){
                total = 0;

                for (int i = 0; i < pokeballs.size(); i++) {

                    if (pokeball.getName().equals(pokeballs.get(i).getName())){
                        total++;
                    }
                }

                System.out.println("\t- " + total + "x " + pokeball.getName());
            }

            System.out.println();
        }
    }
}
