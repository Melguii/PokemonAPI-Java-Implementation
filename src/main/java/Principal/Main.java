package Principal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Jugador.Pokeball;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main (String[] args) throws FileNotFoundException {
        //Leemos los archivos JSON necesarios
        Gson gson = new GsonBuilder().create();

        Ball[] pokeBalls = gson.fromJson(new FileReader("files/balls.json"),Ball[].class);
        Legend[] legends = gson.fromJson(new FileReader("files/balls.json"),Ball[].class);

        //Construimos y llamamos al menu
        Pokeball[] pokePokeball = gson.fromJson(new FileReader("files/balls.json"), Pokeball[].class);
        Menu m = new Menu();
        m.menu();
    }
}
