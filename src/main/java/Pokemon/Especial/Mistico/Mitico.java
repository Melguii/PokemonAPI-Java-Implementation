package Pokemon.Especial.Mistico;

import Jugador.Pokedex;
import Pokemon.Pokemon;

public class Mitico extends Pokemon {
    private SpecialResearch special_research;

    public void getSpecial_Research(Pokedex p) {
        System.out.println("\t- "+special_research.getName() + " (" + getName() + "):");
        special_research.printQuests(p);
    }

    public void setSpecial_Research(SpecialResearch special_research) {
        this.special_research = special_research;
    }
}
