package utils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
public class PokeApi {

    public static final String  URL = "https://pokeapi.co/api/v2/";

    private callToTheServer(String path){
        URL url;
        url = new URL("http://programacionextrema.com");

        URLConnection con = url.openConnection();

        // Leyendo el resultado
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

    }

    public getHeight(int id){

    };


}
