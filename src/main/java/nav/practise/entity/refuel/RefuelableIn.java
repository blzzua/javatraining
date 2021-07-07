package nav.practise.entity.refuel;

import nav.practise.entity.GasQuality;

public interface RefuelableIn {
    double refuelIn(double volumeGas, GasQuality gasQuality);
    double refuelIn(double volumeGas, double gasQuality);


}
