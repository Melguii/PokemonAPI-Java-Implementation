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

    public boolean checkSpecialResearch(int id) {
        boolean completed = true;
        for (Quest q: quests) {
            if (q.getTarget() == id){
                q.AddObtenido();
            }
            if (q.getPercentaje() != 100){
                completed = false;
            }
        }
        return completed;
    }

    public void reset() {
        for (Quest q: quests) {
            q.reset();
        }
    }
}