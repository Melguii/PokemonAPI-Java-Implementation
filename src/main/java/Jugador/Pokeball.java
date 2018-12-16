package Jugador;

public class Pokeball {

    @Override
    public String toString() {
        return "Pokeball{" +
                "name='" + name + '\'' +
                ", capture_rate=" + capture_rate +
                ", price=" + price +
                '}';
    }

    private String name;
    private int capture_rate;
    private int price;

    public void setName(String name) {
        this.name = name;
    }

    public void setCapture_rate(int capture_rate) {
        this.capture_rate = capture_rate;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getCapture_rate() {
        return capture_rate;
    }

    public int getPrice() {
        return price;
    }
}