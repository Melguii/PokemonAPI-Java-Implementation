package Pokemon.Especial.Mistico;

public class Quest {

    private int target;
    private int quantity;

    private int obtenidos;

    private int percentaje;

    /**
     * Devuelde el porcentaje de una quest.
     * @return percentaje
     */
    public int getPercentaje() {
        return percentaje;
    }

    /**
     * Devuelve cuantos pokemons hemos capturado de ese tipo
     * @return obtenidos
     */
    public int getObtenidos() {
        return obtenidos;
    }

    /**
     * Añade un pokemon obtenido y actualiza el porcentaje
     */
    public void AddObtenido() {
        this.obtenidos ++;
        setPercentaje();
    }

    /**
     * Actualiza el porcentaje
     */
    private void setPercentaje() {
        percentaje =  obtenidos / quantity * 100;
    }

    /**
     * Resetea la quest
     */
    public void reset(){
        obtenidos = 0;
        percentaje = 0;
    }

    /**
     * Obtiene el pokemon que hay que capturar
     * @return target
     */
    public int getTarget() {
        return target;
    }

    /**
     * Set que indica cual es el target
     * @param target
     */
    public void setTarget(int target) {
        this.target = target;
    }

    /**
     * Getter de la cantidad de un pokemon a capturar
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter de la cantidad de un pokemon a capturar
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Printa la quest por pantalla
     * @param name
     */
    public void printQuest(String name) {
        System.out.println("\t\t* Capturar " + name + ": " + obtenidos + "/" + quantity + " (" + percentaje +"%)");
    }
}