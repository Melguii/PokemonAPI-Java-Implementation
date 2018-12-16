package Principal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Jugador.Pokeball;
import utils.DataManager;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main (String[] args) throws FileNotFoundException {
        DataManager dataManager = new DataManager();
        //Menu m = new Menu();

        dataManager.loadData();
        dataManager.print();


        //Construimos y llamamos al menu
        //Pokeball[] pokePokeball = gson.fromJson(new FileReader("files/balls.json"), Pokeball[].class);
        //m.menu();
    }
}
