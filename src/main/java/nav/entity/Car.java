package nav.entity;

public class Car {

    //Заводские х-стики
    private final int acceleration;
    private final int deceleration;
    private final int maxVelocity;
    private final int volumeGasTankMax;
    private final int gasConsumptionPerCycle;

    //Изменяемые при работе
    private boolean powerOn;
    private int velocity;
    private int volumeGasTankCur;
    private Driver driver;


    public Car(int acceleration,
               int deceleration,
               int maxVelocity,
               int volumeGasTankMax,
               int gasConsumptionPerCycle) {
        this.acceleration = acceleration;
        this.deceleration = deceleration;
        this.maxVelocity = maxVelocity;
        this.volumeGasTankMax = volumeGasTankMax;
        this.gasConsumptionPerCycle = gasConsumptionPerCycle;
    }

    public void setDriver(Driver driver) {
        if (driver != null && this.velocity == 0) {
            this.driver = driver;
        }
    }

    public void unsetDriver() {
        if (this.driver != null && this.velocity == 0) {
            this.driver = null;
        }
    }

    public Driver getDriver() {
        return driver;
    }

    public void perIteration() {
        if (this.volumeGasTankCur == 0) {
            this.powerOn = false;
        }

        if (this.powerOn) {
            this.volumeGasTankCur--;
        } else {
            dec();
        }
    }

    public void acc() {
        if (this.powerOn && this.velocity != this.maxVelocity) {
            int factAccMax = this.maxVelocity - this.velocity;
            int factAcc = this.acceleration > factAccMax
                    ? factAccMax
                    : this.acceleration;
            this.velocity += factAcc;
        }
    }

    public void dec() {
        if (this.velocity >= 0) {
            this.velocity = this.deceleration > this.velocity
                    ? 0
                    : this.velocity - this.deceleration;
        }
    }

    public boolean powerSwitch() {
        this.powerOn = driver != null && volumeGasTankCur > 0 && !this.powerOn;
        return this.powerOn;
    }

    public int refuel(int volumeGas) {
        int gasFact = 0;

        if (volumeGas > 0 &&
                this.velocity == 0 &&
                !this.powerOn &&
                this.volumeGasTankMax != this.volumeGasTankCur) {
            int volumeAvailableLeft = this.volumeGasTankMax - this.volumeGasTankCur;
            gasFact = volumeAvailableLeft > volumeGas ? volumeGas : volumeAvailableLeft;
            this.volumeGasTankCur += gasFact;
        }

        return gasFact;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public int getDeceleration() {
        return deceleration;
    }

    public int getMaxVelocity() {
        return maxVelocity;
    }

    public int getVolumeGasTankMax() {
        return volumeGasTankMax;
    }

    public int getGasConsumptionPerCycle() {
        return gasConsumptionPerCycle;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getVolumeGasTankCur() {
        return volumeGasTankCur;
    }
}
