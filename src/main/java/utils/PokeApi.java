package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class PokeApi {
    public static final String  URL = "https://pokeapi.co/api/v2/";

    public static void main(String[] args){
        PokeApi p = new PokeApi(135);
        System.out.println(p.getFlavorTextEnglish());
    }
    private int id;
    private JsonObject pokemon;
    private JsonObject pokemonSpecies;

    public PokeApi(int id){
        pokemon = callToTheServer("pokemon/" + id + "/");
        pokemonSpecies = callToTheServer("pokemon-species/" + id + "/");
    }

    private JsonObject callToTheServer(String path){
        URL url;
        JsonParser parser = new JsonParser();
        JsonObject obj;
        JsonReader reader;

        try {
            url = new URL(URL + path);
            URLConnection con = url.openConnection();
            con.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            reader = new JsonReader(new InputStreamReader(con.getInputStream()));
            obj = parser.parse(reader).getAsJsonObject();
        } catch (IOException e) {
            obj = null;
            e.printStackTrace();
        }
        return obj;
    }

    public int getHeight(){
        return pokemon.get("height").getAsInt();
    }

    public int getWeight(){
        return pokemon.get("weight").getAsInt();
    }

    public int getBaseExperience(){
        return pokemon.get("base_experience").getAsInt();
    }

    public String getDefaultSprite(){
        JsonObject Sprites = pokemon.getAsJsonObject("sprites");
        return Sprites.get("front_default").getAsString();
    }

    public String getFlavorTextEnglish(){
        JsonArray flavorTextEntries = pokemonSpecies.getAsJsonArray("flavor_text_entries");
        JsonObject object = null;
        JsonObject languaje;
        for (int i = 0; i <flavorTextEntries.size() ; i++) {
            object = (JsonObject) flavorTextEntries.get(i);
            languaje = (JsonObject)object.get("language");
            if(languaje.get("name").getAsString().equals("en")){
                break;
            }
        }
        return object.get("flavor_text").getAsString();
    }
}
