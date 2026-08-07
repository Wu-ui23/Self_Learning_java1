package citylink.charpter01;

public class CarVehicle extends Vehicle{
    private float carRemainEnergy;
    //保险单号
    private  String carSecurityNumber;

    //get/set 方法
    public float getCarRemainEnergy() {
        return carRemainEnergy;
    }

    public void setCarRemainEnergy(float carRemainEnergy) {
        this.carRemainEnergy = carRemainEnergy;
    }

    public String getCarSecurityNumber() {
        return carSecurityNumber;
    }

    public void setCarSecurityNumber(String carSecurityNumber) {
        this.carSecurityNumber = carSecurityNumber;
    }

    //构造方法
    public CarVehicle(){
    }
    public CarVehicle(String vehicleBeand, String vehicleNumber, float carRemainEnergy, String carSecurityNumber) {
        super(vehicleBeand, vehicleNumber);
        this.carRemainEnergy = carRemainEnergy;
        this.carSecurityNumber = carSecurityNumber;
    }


}
