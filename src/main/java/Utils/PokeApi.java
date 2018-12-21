package Utils;

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
    private int id;
    private JsonObject pokemon;
    private JsonObject pokemonSpecies;

    /**
     * Constructor dado un ID llama al server para obtener los documentos
     * @param id
     */
    public PokeApi(int id){
        this.id = id;
        pokemon = callToTheServer("pokemon/" + id + "/");
        pokemonSpecies = callToTheServer("pokemon-species/" + id + "/");
    }

    /**
     * Llamada al servidor para cualquier url
     * @param path
     * @return documento obtenido de la llamada
     */
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

    /**
     * Getter de height del pokemon
     * @return el valor entero de height del pokémon
     */
    public int getHeight(){
        return pokemon.get("height").getAsInt();
    }

    /**
     * Geeter del weight pel pokemon
     * @return devuelve el valor entero del weight del pokémon
     */
    public int getWeight(){
        return pokemon.get("weight").getAsInt();
    }

    /**
     * Getter de la base_experience del pokémon
     * @return devuelve el valor entero de la base_experience del pokémon
     */
    public int getBaseExperience(){
        return pokemon.get("base_experience").getAsInt();
    }

    /**
     * Getter del Sprite del pokémon
     * @return devuelve el Sprite del pokémon en formato de String
     */
    public String getDefaultSprite(){
        JsonObject Sprites = pokemon.getAsJsonObject("sprites");
        return Sprites.get("front_default").getAsString();
    }

    /**
     * Obtiene la descripcion del pokemon del Jsonobject
     * @return la descripccion.
     */
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
