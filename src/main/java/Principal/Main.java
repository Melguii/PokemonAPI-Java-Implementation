package Principal;

import Jugador.Pokedex;
import Jugador.Tienda;
import utils.ImportData;
import java.io.FileNotFoundException;


public class Main {
    public static void main (String[] args) throws FileNotFoundException {
        //Usuario usuario = new Usuario();
        ImportData dataManager = new ImportData();
        Tienda tienda = new Tienda();
        Pokedex pokedex = new Pokedex();
        //Principal.Menu m = new Principal.Menu();

        tienda.setPokeballs(dataManager.loadDataPokeballs());
        pokedex.setPokedex(dataManager.loadDataPokedex());
        pokedex.printPokedex();

        //Construimos y llamamos al menu
        //Pokeball[] pokePokeball = gson.fromJson(new FileReader("files/balls.json"), Pokeball[].class);
        //m.menu();
    }
}
