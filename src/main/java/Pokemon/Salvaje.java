package Pokemon;

public class Salvaje  extends Pokemon{
    @Override
    public double captureEcuation(double pb) {
        return ((pb/256) + (getCapture_rate()/2048));
    }
}
