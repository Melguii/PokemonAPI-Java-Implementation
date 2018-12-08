import com.Pokemon.Ball;
import com.Pokemon.Menu;
import com.google.gson.Gson;

import java.io.FileReader;

public class Main {
    public static void main (String[] args) throws FileNotFoundException{
        Gson gson = new GsonBuilder().create();
        Ball[] pokeBalls = gson.fromJson(new FileReader("files/balls.json"),Ball[].class);
        Ball[] pokeBalls = gson.fromJson(new FileReader("files/balls.json"),Ball[].class);
        Menu m = new Menu();
        m.menu();
    }
}
