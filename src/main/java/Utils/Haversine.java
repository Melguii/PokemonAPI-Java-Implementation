package Utils;

public class Haversine {
    private final int EARTH_RADIUS = 6371;

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
