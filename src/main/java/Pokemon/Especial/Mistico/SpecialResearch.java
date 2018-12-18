package Pokemon.Especial.Mistico;

import Jugador.Pokedex;

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

    public void printQuests(Pokedex p) {
        for (Quest q: quests) {
            q.printQuest(p);
        }
        System.out.println();
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }

    public void showQuests() {
        for (Quest i:quests) {
            i.showQuest();
        }
    }
}