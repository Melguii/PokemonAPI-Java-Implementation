package Utils;

public class Haversine {
    private final int EARTH_RADIUS = 6371;

    /**
     * Método que mediante la aplicación de una fórmula calcula la distáncia entre dos puntos como si se tratese de dos puntos
     * en el planeta Tierra. Pues bien, esta fórmula nos ayuda a simular su corbatura y la distáncia real entre estos dos puentos
     * descritos por coordenadas.
     *
     * @param latti latitud origen
     * @param loong latitud destino
     * @param finalLatti latitud destino
     * @param finalLoong longitud destino
     * @return la diferéncia entre estos dos puntos en formato double para obtener más precisión
     */
    public double distanceHarven(double latti, double loong, double finalLatti, double finalLoong) {
        double dLat  = Math.toRadians((finalLatti - latti));
        double dLong = Math.toRadians((finalLoong - loong));
        latti = Math.toRadians(latti);
        finalLatti   = Math.toRadians(finalLatti);
        double a =haversin(dLat) + Math.cos(latti) * Math.cos(finalLatti) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    private double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}
