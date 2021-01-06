package Pokemon.Especial.Legendario;

public class Gym {
    private String name;
    private Location location;

    /**
     * Getter del nombre
     * @return Nombre del gym
     */
    public String getName() {
        return name;
    }

    /**
     * Setter del nombre del gym
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter de location
     * @return localicacion del gym
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Setter del location
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

}
