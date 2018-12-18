package Pokemon.Especial.Legendario;

import Pokemon.Pokemon;

import static java.lang.Math.pow;

public class Legendario extends Pokemon {
    private Gym gym;

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    @Override
    public double captureEcuation(double pb) {
        return (pow(pb, 1.5) + pow(getCapture_rate(), Math.PI))/4096;
    }
}
