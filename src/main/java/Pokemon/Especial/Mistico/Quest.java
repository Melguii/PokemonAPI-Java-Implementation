package Pokemon.Especial.Mistico;

public class Quest {

    private int target;
    private int quantity;

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


    public void printQuest(String name) {
        System.out.println("\t\t* Capturar " + name + ": " + obtenidos + "/" + quantity + " (" + percentaje +"%)");
    }
}