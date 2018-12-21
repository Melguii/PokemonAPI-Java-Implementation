package Pokemon;

import Pokemon.Especial.Legendario.Gym;
import Pokemon.Especial.Legendario.Legendario;

public  abstract class Pokemon {


    private String name;
    private int id;
    private int capture_rate;
    private String front_default;
    private String flavor_text;
    private int weight;

    /**
     * Getter de Flavor_text
     * @return devuelve el flavor_text
     */
    public String getFlavor_text() {
        return flavor_text;
    }

    /**
     * Setter de Flavor_text
     * @param flavor_text introduce en el pokemon su Flavor_text
     */
    public void setFlavor_text(String flavor_text) {
        this.flavor_text = flavor_text;
    }

    /**
     * Getter de weight
     * @return devuelve el weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Setter de weight
     * @param weight introduce en el pokemon su weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Getter de height
     * @return devuelve el height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Setter de height
     * @param height introduce en el pokemon su height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Getter de Base_experience
     * @return devuelve la Base_experience
     */
    public int getBase_experience() {
        return base_experience;
    }

    /**
     * Setter de base_experience
     * @param base_experience introduce en el pokemon su base_experience
     */
    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    private int height;
    private int base_experience;

    /**
     * Getter de Front_default
     * @return devuelve el Front_default
     */
    public String getFront_default() {
        return front_default;
    }

    /**
     * Setter de front_default
     * @param front_default introduce en el pokemon su front_default
     */
    public void setFront_default(String front_default) {
        this.front_default = front_default;
    }

    /**
     * Getter de Capture_rate
     * @return devuelve el Capture_rate
     */
    public int getCapture_rate() {
        return capture_rate;
    }

    /**
     * Setter de capture_rate
     * @param capture_rate introduce en el pokemon su capture_rate
     */
    public void setCapture_rate(int capture_rate) {
        this.capture_rate = capture_rate;
    }

    /**
     * Getter de Name
     * @return devuelve el Name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter de name
     * @param name introduce en el pokemon su name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter de Id
     * @return devuelve el Id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de id
     * @param id introduce en el pokemon su id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método abstracto (polimorfismo) implementado dentro de los diferentes tipos de pokémon. Este devuelve la probabilidad
     * de captura del pokémon.
     *
     * @param pb: capture_rate de la pokeball
     * @return Devuelve la probabilida de captura del pokémon
     */
    public abstract double captureEcuation(double pb);

}