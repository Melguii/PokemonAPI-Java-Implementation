package Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tienda {
    private Pokeball[] pokeballs;

    /**
     *Getter del array de pokeballs
     * @return Devuelve el array de pokeballs
     */
    public Pokeball[] getPokeballs() {
        return pokeballs;
    }

    /**
     * Setter de el array de pokeballs
     * @param pokeballs registra un array de pokeballs
     */
    public void setPokeballs(Pokeball[] pokeballs) {
        this.pokeballs = pokeballs;
    }

    /**
     * Mostramos todos los objetos de la tienda con sus precios correspondientes
     */
    public void mostrarObjetos() {
        char i = 'a';
        System.out.println("Pokéballs disponibles:");

        for (Pokeball pokeActual: pokeballs) {
            System.out.println(i++ + ") " + pokeActual.getName() + ":\t" + pokeActual.getPrice() + " monedes");
        }
        System.out.println("\n"+ i + ") " + "Sortir sense comprar\n");
    }

    /**
     * Devolvemos tantos objetos del objeto que quiere el usuario
     * @param eleccion el nombre del objeto
     * @return la lista de todos los objetos (pokeball) que quiere el usuario
     */
    public List<Pokeball> getObjetos(char eleccion) {
        List<Pokeball> pokeballs = new ArrayList<Pokeball>();
        int index = eleccion - 'A';
        if (index > this.pokeballs.length-1 || index < 0){
            return  null;
        }else{
            System.out.println("Quantes unitats en vol comprar?");
            Scanner sc = new Scanner(System.in);
            int cuantas = sc.nextInt();

            if(cuantas < 1){

                return null;

            }else{
                for (int i = 0; i < cuantas; i++) {
                    pokeballs.add(this.pokeballs[index]);
                }
                return pokeballs;
            }
        }
    }

    /**
     *???????????????????????????
     * @return
     */
    public Pokeball getFirstPokeball() {
        return pokeballs[0];
    }
}
