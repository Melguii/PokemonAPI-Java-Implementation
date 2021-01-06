package Utils;

import Pokemon.Especial.Legendario.Legendario;
import Pokemon.Especial.Mistico.Mitico;
import Pokemon.*;
import com.google.gson.*;
import java.lang.reflect.Type;

public class IdDeserializer implements JsonDeserializer<Pokemon> {
    @Override
    /**
     * Crea un JsonObject con el Json y decide donde guardar la informacion basado en el kind del pokemon
     */
    public Pokemon deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement kind = jsonObject.get("kind");

        if (kind != null){
            switch (kind.getAsString()){
                case "legendary":
                    return context.deserialize(jsonObject, Legendario.class);
                case "mythical":
                    return context.deserialize(jsonObject, Mitico.class);
            }
        }else{
            return context.deserialize(jsonObject, Salvaje.class);
        }

        return null;
    }
}
