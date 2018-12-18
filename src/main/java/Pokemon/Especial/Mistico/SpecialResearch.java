package Pokemon.Especial.Mistico;
import java.util.ArrayList;


public class SpecialResearch {

    private String name;

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    private ArrayList<Quest> quests;

    public SpecialResearch() {
        this.quests = new ArrayList<Quest>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setQuests(ArrayList<Quest> quests) {
        this.quests = quests;
    }

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

    public void reset() {
        for (Quest q: quests) {
            q.reset();
        }
    }
}