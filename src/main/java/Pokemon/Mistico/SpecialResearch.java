package Pokemon.Mistico;

import Pokemon.Mistico.Quest;

import java.util.ArrayList;
import java.util.List;

public class SpecialResearch {

    private String name;
    private List<Quest> quests;

    public SpecialResearch() {
        this.quests = new ArrayList<Quest>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }

}