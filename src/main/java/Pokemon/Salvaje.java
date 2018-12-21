package Pokemon;

public class Salvaje  extends Pokemon{

    /**
     * Método aplicado en un polimorfismo que calcula la probabilidad de captura de un pokemon salvaje
     * @param pb: el capture_rate de la pokeball
     * @return el valor que define la probabilidad de captura
     */
    @Override
    public double captureEcuation(double pb) {
        return ((pb/256) + (getCapture_rate()/2048));
    }
}
