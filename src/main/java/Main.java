import com.Pokemon.Menu;
import com.google.gson.Gson;

import java.io.FileReader;

public class Main {
    public static void Main (String[] args){
        Gson gson = new Gson();

        Menu m = new Menu();
        m.menu();

        //FileReader f = m.menu();
        //User[] users = gson.fromJson(f, User[].class);
    }
}
