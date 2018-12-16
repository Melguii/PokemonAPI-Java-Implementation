package Jugador;

public class Tienda {
    private Pokeball[] pokeballs;

    public Pokeball[] getPokeballs() {
        return pokeballs;
    }

    public void setPokeballs(Pokeball[] pokeballs) {
        this.pokeballs = pokeballs;
    }

    public void mostrarObjetos() {
        char i = 'a';
        System.out.println("Pokéballs disponibles:");

        for (Pokeball pokeActual: pokeballs) {
            System.out.println(i++ + ") " + pokeActual.getName() + ":\t" + pokeActual.getPrice() + " monedes");
        }
        System.out.println("\n"+i++ + ") " + "Sortir sense comprar\n");
    }
}
