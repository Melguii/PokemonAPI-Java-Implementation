package Pokemon;

public  abstract class Pokemon {


    private String name;
    private int id;
    private int capture_rate;
    private String front_default;
    private String flavor_text;
    private int weight;
    private int height;
    private int base_experience;


    public int getCapture_rate() {
        return capture_rate;
    }

    public void setCapture_rate(int capture_rate) {
        this.capture_rate = capture_rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}