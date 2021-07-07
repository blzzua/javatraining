package nav.practise.entity.barrels;

import nav.practise.entity.GasQuality;
import nav.practise.entity.refuel.RefuelableIn;
import nav.practise.entity.refuel.RefuelableOut;

public class Canister implements RefuelableIn, RefuelableOut {
    private  final  double gasVolumeMax;
    private double gasVolumeCur;
    private double gasQuality;

    public Canister(double gasVolumeMax){
        this.gasVolumeMax = gasVolumeMax;
    }


    @Override
    public double refuelIn(double volumeGas, GasQuality gasQuality) {
        return refuelIn(volumeGas, gasQuality.getAccCoef());
    }

    @Override
    public double refuelIn(double volumeGas, double gasQuality) {
        if (volumeGas > 0 ){
            this.gasQuality = (this.gasVolumeCur * this.gasQuality + volumeGas * gasQuality) /(this.gasVolumeCur + volumeGas) ;
            this.gasVolumeCur = Math.min(this.gasVolumeCur + volumeGas, this.gasVolumeMax);
        }
        return volumeGas;
    }


    @Override
    public double refuelOut(double volumeGas, RefuelableIn refuelable) {
        double gasFact = Math.min(volumeGas, this.gasVolumeCur );
        refuelable.refuelIn(gasFact, this.gasQuality);

        this.gasVolumeCur -= gasFact;
        return gasFact;
    }
}
