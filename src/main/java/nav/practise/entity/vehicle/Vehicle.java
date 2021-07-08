package nav.practise.entity.vehicle;

import nav.practise.entity.Driver;
import nav.practise.entity.barrels.Canister;
import nav.practise.entity.refuel.RefuelableIn;
import nav.practise.entity.scheduling.Iterating;

public interface Vehicle extends RefuelableIn, Iterating {

    void setDriver(Driver driver);

    void unsetDriver();

    void acc();

    void acc(double coef);

    void dec();

    void dec(double coef);

    boolean powerSwitch();

    double getDistance();

    double getAcceleration();

    double getDeceleration();

    double getMaxVelocity();

    double getVolumeGasTankMax();

    double getGasConsumptionPerCycle();

    double getVelocity();

    double getVolumeGasTankCur();

    double getGasQuality();

    boolean isPowerOn();

    Driver getDriver();

    void addCanister(Canister canister);
    long getCanistersCount();
    Canister getCanister(int id);
}
