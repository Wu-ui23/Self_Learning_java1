package citylink.charpter01;

public class BicycleVehicle extends Vehicle{
    private float bicycleRemaimElectricity;

    //get/set
    public float getBicycleRemaimElectricity() {
        return bicycleRemaimElectricity;
    }
    public void setBicycleRemaimElectricity(float bicycleRemaimElectricity) {
        this.bicycleRemaimElectricity = bicycleRemaimElectricity;
    }

    //构造方法
    public BicycleVehicle(){}
    public BicycleVehicle(String vehicleBeand, String vehicleNumber, float bicycleRemaimElectricity) {
        super(vehicleBeand, vehicleNumber);
        this.bicycleRemaimElectricity = bicycleRemaimElectricity;
    }
}
