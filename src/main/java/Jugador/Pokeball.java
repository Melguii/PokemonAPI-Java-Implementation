package Jugador;

public class Pokeball {

    //Atributos de la clase
    private String name;
    private int capture_rate;
    private int price;

    //Geters and setters

    /**
     * Setter de name
     * @param name inserta el name de la pokeball
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter de capture_rate
     * @param capture_rate inserta la capture_rate de la pokeball
     */
    public void setCapture_rate(int capture_rate) {
        this.capture_rate = capture_rate;
    }

    /**
     * Setter de price
     * @param price inserta el price de la pokeball
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Getter de Name
     * @return devuelve el name de la pokeball
     */
    public String getName() {
        return name;
    }

    /**
     * Getter de capture_rate
     * @return devuelve la capture_rate de la pokeball
     */
    public int getCapture_rate() {
        return capture_rate;
    }

    /**
     * Getter de price
     * @return devuelve el price de la pokeball
     */
    public int getPrice() {
        return price;
    }

}