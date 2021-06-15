package phone;

public class SmartPhone {
    protected String ModelName;
    private String LaunchYear;
    Dimension Dimension = new Dimension();
    private Camera MainCamera;
    private Camera SelfieCamera;
    Display Display;
    private Float Battery; // mAh
    private Float ChargeLevel = 0F; // percent
    private boolean isFastCharge;
    private float mainMemory; // gb
    String MemoryTechnology; // UFS, lpddr
    private boolean isCardSlot;
    private boolean isNFC;
    String Port; // microusb, typec

    public SmartPhone(String ModelName) {
        this.ModelName = ModelName;
    }

    public String getLaunchYear() {
        return LaunchYear;
    }

    public void setLaunchYear(String launchYear) {
        LaunchYear = launchYear;
    }

    public void setDimension(Dimension dimension) {
        Dimension = dimension;
    }

    public void setDimension(float width, float height) {
        this.Dimension.height = height;
        this.Dimension.width  = width;
    }

    public Camera getMainCamera() {
        return MainCamera;
    }

    public void setMainCamera(float resolution, boolean HDR ) {
        this.MainCamera =  new Camera(resolution);
        this.MainCamera.HDR = HDR;
    }

    public Camera getSelfieCamera() {
        return SelfieCamera;
    }

    public void setSelfieCamera(Camera selfieCamera) {
        SelfieCamera = selfieCamera;
    }

    public phone.Display getDisplay() {
        return Display;
    }

    public void setDisplay(phone.Display display) {
        Display = display;
    }

    public Float getBattery() {
        return Battery;
    }

    public void setBattery(Float battery) {
        Battery = battery;
    }

    public boolean isFastCharge() {
        return isFastCharge;
    }

    public void setFastCharge(boolean fastCharge) {
        isFastCharge = fastCharge;
    }

    public float getMainMemory() {
        return mainMemory;
    }

    public void setMainMemory(float mainMemory) {
        this.mainMemory = mainMemory;
    }

    public String getMemoryTechnology() {
        return MemoryTechnology;
    }

    public void setMemoryTechnology(String memoryTechnology) {
        MemoryTechnology = memoryTechnology;
    }

    public boolean isCardSlot() {
        return isCardSlot;
    }

    public void setCardSlot(boolean cardSlot) {
        isCardSlot = cardSlot;
    }

    public boolean isNFC() {
        return isNFC;
    }

    public void setNFC(boolean NFC) {
        isNFC = NFC;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
/*
        +protected String ModelName;
        +String LaunchYear;
        +Dimension Dimension = new Dimension();
        +Camera MainCamera;
        +Camera SelfieCamera;
        Display Display;
        Float Battery; // mAh
        boolean isFastCharge;
        +float mainMemory; // gb
        +String MemoryTechnology; // UFS, lpddr
        boolean isCardSlot;
        boolean isNFC;
        String Port; // microusb, typec
*/
        sb.append("Smartphone ").append(this.ModelName);
        if (null != LaunchYear){
            sb.append(" launched at ").append(this.LaunchYear).append(" year,");
        }
        if (null != this.Dimension){
            sb.append(" size: ").append(this.Dimension.width).append("x").append(Dimension.height).append(" mm");
        }
        if (null != this.MainCamera){
            sb.append(", camera ").append(String.format("%d",(long)this.MainCamera.Resolution)).append("MP");
            if (null != this.SelfieCamera){
                sb.append(" and frontal one ").append(String.format("%d",(long)this.SelfieCamera.Resolution)).append("MP");
            }
        }
        if (0F != this.mainMemory){
            sb.append(" memory: ").append(this.mainMemory).append(" GB");
            if (null != this.MemoryTechnology){
                sb.append(" (").append(this.MemoryTechnology).append(")");
            }
        }
        return sb.toString();
    }

    public void ChargeDuringTime(float mA, float durationSec){
        /* если фастчаржа нет тогда максимальный ток зарядки будет будем считать 500ма */
        Float consume_mA = this.isFastCharge ? mA : 500F ;
        Float durationHours = durationSec / 3600;
        Float remainingChargeMAH = ( 1 - this.ChargeLevel / 100 ) * this.Battery;
        if ( remainingChargeMAH < durationHours * consume_mA){
            this.ChargeLevel = 100F;
        }
        else {
            this.ChargeLevel += 100 * ( durationHours * consume_mA ) / this.Battery;
        }

    }
    public Float getChargeLevel() {
        return ChargeLevel;
    }

};
