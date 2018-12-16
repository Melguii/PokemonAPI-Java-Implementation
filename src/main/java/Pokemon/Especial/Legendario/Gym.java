package Pokemon.Especial.Legendario;

public class Gym {
    @Override
    public String toString() {
        return "Gym{" +
                "name='" + name + '\'' +
                ", location=" + location +
                '}';
    }

    private String name;
    private Location location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
