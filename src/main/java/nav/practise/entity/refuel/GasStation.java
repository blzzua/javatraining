package nav.practise.entity.refuel;

import nav.practise.entity.GasQuality;

public class GasStation {

    private static GasStation instance;
    private Gasoline[] gasolines;

    private GasStation(Gasoline[] gasolines) {
        this.gasolines = gasolines;
    }

    public static GasStation getInstance() {
        if (instance == null) {
            instance = new GasStation(new Gasoline[]{
                    new Gasoline(GasQuality.REACTIVE_FUEL, 1000),
                    new Gasoline(GasQuality.GOOD, 1000),
                    new Gasoline(GasQuality.NORMAL, 1000),
                    new Gasoline(GasQuality.BAD, 1000),
                    new Gasoline(GasQuality.ALMOST_WATER, 1000)
            });
        }
        return instance;
    }

    public double refuelOut(int num, double volumeGas, RefuelableIn refuelableIn) {
        double result = 0;

        if (num >= 0 || num < gasolines.length) {
            Gasoline gasoline = gasolines[num];
            result = gasoline.refuelOut(volumeGas, refuelableIn);
        }

        return result;
    }

    public int getGasolineCount() {
        return gasolines.length;
    }


    public double refuelOut(double volumeGas, RefuelableIn refuelableIn) {
        return 0;
    }
}
