package nav.practise.entity.refuel;

import nav.practise.entity.GasQuality;

public class Gasoline implements RefuelableOut {

    private final GasQuality gasQuality;
    private double leftGas;

    public Gasoline(GasQuality gasQuality, double leftGas) {
        this.gasQuality = gasQuality;
        this.leftGas = leftGas;
    }

    public double refuelOut(double volumeGas, RefuelableIn refuelableIn) {
        double result = 0;

        if (volumeGas > 0 && refuelableIn != null) {
            double gasPotential = this.leftGas > volumeGas ? volumeGas : this.leftGas;
            double gasFact = refuelableIn.refuelIn(gasPotential, gasQuality);
            this.leftGas = this.leftGas - gasFact;
            result = gasQuality.getPricePerLiter() * gasFact;
        }

        return result;
    }

    public GasQuality getGasQuality() {
        return gasQuality;
    }
}
