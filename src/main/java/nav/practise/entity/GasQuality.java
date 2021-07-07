package nav.practise.entity;

public enum GasQuality {

    REACTIVE_FUEL(2.0, 10),
    GOOD(1.1, 6),
    NORMAL(1.0, 5),
    BAD(0.9, 5),
    ALMOST_WATER(0.2, 4);

    private double accCoef;
    private double pricePerLiter;

    GasQuality(double accCoef, double pricePerLiter) {
        this.accCoef = accCoef;
        this.pricePerLiter = pricePerLiter;
    }

    public double getAccCoef() {
        return accCoef;
    }

    public double getPricePerLiter() {
        return pricePerLiter;
    }
    public static boolean isValidFuel(String value){
        for (GasQuality enumValues : GasQuality.values()) {
            if (value.equals(enumValues.name())) {
                return true;
            }
        }
        return false;
    }
}
