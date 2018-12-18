package Pokemon.Especial.Mistico;

import Jugador.Pokedex;

public class Quest {

    private int target;
    private int quantity;

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private int obtenidos;

    private int percentaje;

    public int getPercentaje() {
        return percentaje;
    }

    public int getObtenidos() {
        return obtenidos;
    }

    public void AddObtenido() {
        this.obtenidos ++;
        setPercentaje();
    }

    private void setPercentaje() {
        percentaje =  obtenidos / quantity * 100;
    }


    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void showQuest() {
    }

    public void printQuest(Pokedex p) {
        System.out.println("\t\t* Capturar " + p.getPokemonById(target).getName() + ": " + obtenidos + "/" + quantity + " (" + percentaje +"%)");
    }
}