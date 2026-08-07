package citylink.charpter01;

public abstract class Vehicle {
    private String vehicleBrand;
    private String vehicleNumber;
    private String vehicleStatus;

    //get/set 方法
    public String getVehicleBrand() {
        return vehicleBrand;
    }
    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    public String getVehicleStatus() {
        return vehicleStatus;
    }
    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    //构造函数
    public Vehicle(){
    }
    public Vehicle(String vehicleBeand,String vehicleNumber){
        this.vehicleBrand = vehicleBeand;
        this.vehicleNumber = vehicleNumber;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleBeand='" + vehicleBrand + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleStatus='" + vehicleStatus + '\'' +
                '}';
    }


}




