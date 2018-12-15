package Principal;

import Pokemon.Ball;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main (String[] args) throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();
        Ball[] pokeBalls = gson.fromJson(new FileReader("files/balls.json"), Ball[].class);
        Menu m = new Menu();
        m.menu();
    }
}
