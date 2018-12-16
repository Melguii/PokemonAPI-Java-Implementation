package Pokemon;

public  abstract class Pokemon {
    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    private String name;
    private int id;

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