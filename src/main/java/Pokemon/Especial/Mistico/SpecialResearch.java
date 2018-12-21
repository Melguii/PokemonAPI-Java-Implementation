package Pokemon.Especial.Mistico;
import java.util.ArrayList;


public class SpecialResearch {

    private String name;
    private ArrayList<Quest> quests;


    /**
     * Devuelve las quest del pokemon
     * @return quests
     */
    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public SpecialResearch() {
        this.quests = new ArrayList<Quest>();
    }

    /**
     * Devuelve el nombre
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Pone el nombre a la SpecialResearch
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Hace set de las quests
     * @param quests
     */
    public void setQuests(ArrayList<Quest> quests) {
        this.quests = quests;
    }

    /**
     * Comprueba que una quest esta cumplida o no, si esta cumplida lo actualiza
     * @param id
     * @return el porcentaje total de la SepecialResearch
     */
    public int checkSpecialResearch(int id) {
        int sum = 0;
        for (Quest q: quests) {
            if (q.getTarget() == id){
                q.AddObtenido();
            }
            sum += q.getPercentaje();
        }
        return sum/quests.size();
    }

    /**
     * Resetea la research
     */
    public void reset() {
        for (Quest q: quests) {
            q.reset();
        }
    }
}