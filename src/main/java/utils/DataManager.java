package utils;

import Jugador.Pokeball;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class DataManager {
    private static final String PATH = "files/";
    private static final String FILE1 = "balls.json";
    private static final String FILE2 = "legends.json";
    private static final String FILE3 = "poke.json";
    private static final String separator = System.lineSeparator();

    //INFORMATION THAT WE READ

    private Pokeball[] pokeballs;




    public loadData(){
        Gson gson = new Gson();
        JsonReader reader;

        try {
            reader = new JsonReader(new FileReader(PATH + FILE1));
            pokeballs = gson.fromJson(reader, Pokeball[].class);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
