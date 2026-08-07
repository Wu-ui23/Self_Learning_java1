package citylink.charpter01;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//定义租车信息
class RentInfo{
    private User user = null;
    private String rentTime = "";
    private String backTime = "";
    private  int vehicleType = 0;
    private long startPosition ;
    private long endPosition;
    private Vehicle rentVehicle = null;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getRentTime() {
        return rentTime;
    }
    public void setRentTime(String rentTime) {
        this.rentTime = rentTime;
    }

    public int getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(int vehicleType) {
        this.vehicleType = vehicleType;
    }

    public long getStartPosition() {
        return startPosition;
    }
    public void setStartPosition(long startPosition) {
        this.startPosition = startPosition;
    }

    public long getEndPosition() {
        return endPosition;
    }
    public void setEndPosition(long endPosition) {
        this.endPosition = endPosition;
    }

    public String getBackTime() {
        return backTime;
    }
    public void setBackTime(String backTime) {
        this.backTime = backTime;
    }

    public Vehicle getRentVehicle() {
        return rentVehicle;
    }
    public void setRentVehicle(Vehicle rentVehicle) {
        this.rentVehicle = rentVehicle;
    }


    @Override
    public String toString() {
        return "RentInfo{" +
                "user=" + user.getName() +
                ", rentTime='" + rentTime + '\'' +
                ", backTime='" + backTime + '\'' +
                ", vehicleType=" + vehicleType +
                ", startPosition=" + startPosition +
                ", endPosition=" + endPosition +
                ", rentVehicle=" + rentVehicle +
                '}';
    }
}



public class CityLinkPlatform {

    //平台最大允许
    private final int MAX_RANT_BICYCLE_VEHICLE = 100;
    private final int MAX_RANT_CAR_VEHICLE = 100;
    //当前租出车辆
    private int current_bicycle_rented_num = 0;
    private int current_car_rented_num = 0;
   //创建一个
    private  ArrayList<User> registedUsers = new ArrayList<>();  //声明类型，而不是创建实例
    private int index = 0;

    //记录已注册用户
    private int currentRegistedUserCount = 1;

    //记录当前登入用户
    private User currentLoginUser ;

    //单价
    private float bicyclePrice = 1.0f;
    private float carPrice = 5.0f;

    //平台记录当前用户租车信息
    ArrayList<RentInfo>  currentRentInfo = new ArrayList<>();

    //平台所有交通工具的信息
    private ArrayList<Vehicle> allVehicleInfo = new ArrayList<>();

    //get /set 方法
    public int getIndex() {
        return index;
    }
    public void   setCurrentLoginUser(User currentLoginUser) {
        this.currentLoginUser = currentLoginUser;
    }
    public User getCurrentLoginUser() {
        return currentLoginUser;
    }
    //让外部访问该
    public ArrayList<User> getRegistedUsers() {
        return registedUsers;
    }

    //构造函数，添加系统默认用户
    public CityLinkPlatform(int type) throws IOException {
        //平台初始化时，添加admin默认用户
        User adminUser = new AdminUser();//多态 (创建对象)
        this.registedUsers.add(adminUser);
        index++;
        //加载所有交通工具信息
        loadCarInfo();
        loadBicycleInfo();
        //加载所有注册用户数据
        loadAllUserInfos(type);
        //打印所有已注册用户信息
        printAllUser();


    }


    /*
        加载所有注册用户数据
         type   1:file   2:DB
     */
    public boolean loadAllUserInfos(int type) throws IOException {
        boolean result = false;
        if(1 == type){
            UserFileOpen fileOpen = new UserFileOpen();
            registedUsers = fileOpen.readUserDatas();

        } else if (2 == type) {
            UserDBOPen dboPen = new UserDBOPen();
            registedUsers = dboPen.readUserDatas();
        }

        return result;
    }


    //
    public boolean writerUserInfo(User user) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(
                "D:\\ToolAPP\\JAVATools\\IDEA\\ProjectOne\\untitled01\\resources\\user.txt", true));
        //写入文件
        writer.newLine();
        writer.write(user.toString());
        //关闭文件
        if(writer != null){
            writer.close();
        }
        return true;
    }




    // 汽车信息
    public void loadCarInfo(){
        for(int i = 0; i <MAX_RANT_CAR_VEHICLE; i++){
            CarVehicle carVehicle = new CarVehicle();
            String carProductNumber = "2_" + System.currentTimeMillis() + "_" + carVehicle.hashCode();
            carVehicle.setCarSecurityNumber(carProductNumber);
            carVehicle.setCarRemainEnergy(1.0f);
            carVehicle.setVehicleBrand("BMW");
            carVehicle.setVehicleNumber(carProductNumber);
            carVehicle.setVehicleStatus("待命中");
            //保存当前车辆信息
            allVehicleInfo.add(carVehicle);
        }
    }

    //单车信息
    public void loadBicycleInfo(){
        for (int i = 0; i <MAX_RANT_BICYCLE_VEHICLE; i++){
            BicycleVehicle bicycleVehicle = new BicycleVehicle();
            String bicycleProductNumber = "1_" + System.currentTimeMillis() + "_" + bicycleVehicle.hashCode();
            bicycleVehicle.setBicycleRemaimElectricity(1.0f);
            bicycleVehicle.setVehicleBrand("凤凰");
            bicycleVehicle.setVehicleNumber(bicycleProductNumber);
            bicycleVehicle.setVehicleStatus("待命中");
            //保存
            allVehicleInfo.add(bicycleVehicle);
        }
    }


    //新增用户                      传入参数
    public boolean addUser(User user){
        registedUsers.add(user);//调用传入的参数，加入到该数组中
        index++;
        printAllUser();
        return true;
    }

    //打印所有用户信息
    public void printAllUser(){
        System.out.println("*** 当前所有用户类别如下：");
        for(int i = 0; i < registedUsers.size(); i++){
            User user = registedUsers.get(i);
            user.printUserInfo();
        }
    }


    //用户租车
    public boolean userRentVehicle(int rentType){
        boolean isRentable = true;  //标记，该平台可租车

        switch (rentType){
            case 1://自行车
                if(current_bicycle_rented_num >= MAX_RANT_BICYCLE_VEHICLE)
                    isRentable = false;
                break;
            case 2://汽车
                if(current_car_rented_num >= MAX_RANT_CAR_VEHICLE)
                    isRentable = false;
                break;
        }
        if(!isRentable){
            System.out.println("+++ 该平台暂不支持租车");
            return true;
        }
        //判断当前用户是否能租车
        boolean result = checkUserCanRentVehicle();
        if(!result){
            System.out.println("+++当前用户租车正在进行，无法租车~");
            return false;
        }

        //租车时间
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentRentTime = formatter.format(localDateTime);
        System.out.println("当前时间为：" + currentRentTime);
        //租车的起始位置：时间戳模拟
        long currentPosition = System.currentTimeMillis();
        //创建对象
        RentInfo rentInfo = new RentInfo();
        rentInfo.setUser(currentLoginUser);
        rentInfo.setVehicleType(rentType);
        rentInfo.setRentTime(currentRentTime);
        rentInfo.setStartPosition(currentPosition);
        rentInfo.setEndPosition(currentPosition);
        //绑定用户和车辆编码
        Vehicle vehicle = getStandbyVehicle(rentType);
        rentInfo.setRentVehicle(vehicle);
        //保存租车信息到平台
        currentRentInfo.add(rentInfo);
        //修改指定类别车辆的数量
        if(1 == rentType)
            current_bicycle_rented_num++;
        else if(2 == rentType)
            current_car_rented_num++;
        //打印租车信息
        System.out.println("当前租车信息"+ rentInfo);
        return true;
    }



    //判断用户是否能租车
    public boolean checkUserCanRentVehicle(){
        boolean result = true;
        //循环遍历集合，匹配当前用户是否租车
        for(int i = 0; i < currentRentInfo.size(); i++){
            RentInfo rentInfo = currentRentInfo.get(i);
            if(rentInfo.getUser() == currentLoginUser){
                result = false;
                break;
            }
        }
        return result;
    }



    //在车辆列表中匹配待命中的车，并修改车辆状态
    public Vehicle getStandbyVehicle(int rentType) {
        Vehicle vehicle = null;
        if (1 == rentType) {
            //遍历车辆列表
            for (int i = 0; i < allVehicleInfo.size(); i++) {
                Vehicle tempVehicle = allVehicleInfo.get(i);
                // 对象 instanceof 类型    -->   判断该对象是否属于该类型，或该类型的子类
                if (tempVehicle instanceof BicycleVehicle &&
                        tempVehicle.getVehicleStatus().equals("待命中")) {
                    vehicle = tempVehicle;
                    vehicle.setVehicleStatus("使用中");
                    break;
                }
            }
        } else if (2 == rentType) {
            //汽车
            for (int i = 0; i < allVehicleInfo.size(); i++) {
                Vehicle tempVehicle = allVehicleInfo.get(i);
                if (tempVehicle instanceof CarVehicle &&
                        tempVehicle.getVehicleStatus().equals("待命中")) {
                    vehicle = tempVehicle;
                    vehicle.setVehicleStatus("使用中");
                    break;
                }
            }
        }
        return vehicle;
    }



    //用户还车
    public boolean userBackVehicle(){
        //还车时间
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentBackTime = formatter.format(localDateTime);
        System.out.println("当前时间为：" + currentBackTime);
        //还车的结束位置：时间戳模拟
        long currentPosition = System.currentTimeMillis();

        //找到当前用户信息
        RentInfo rentInfo = null;
        for(RentInfo info : currentRentInfo){  // currentRentInfo 是列表（集合），其中封装了多个对象，要从中取出，再进行调用
            if(info.getUser().getName().equals(
                    currentLoginUser.getName()
            )) {
                //把找到的对象赋值给rentIfo对象，进行调用
                rentInfo = info;
                break;
            }
        }
        //设置还车时间和地点
        rentInfo.setBackTime(currentBackTime);
        rentInfo.setEndPosition(currentPosition);


        //归还车辆，并修改车辆状态
        for (int i = 0; i < currentRentInfo.size(); i++){
            RentInfo info = currentRentInfo.get(i);
            Vehicle vehicle = currentRentInfo.get(i).getRentVehicle();
            if(vehicle.getVehicleStatus().equals("使用中") &&
                    info.getUser().getName() == currentLoginUser.getName()){
                vehicle.setVehicleStatus("待命中");
            }
        }

        //计费逻辑
        float money = currentLoginUser.getAccountmoney() - userCountPrice(rentInfo);
        //记录车辆归还
        if(rentInfo.getVehicleType() == 1){
            current_bicycle_rented_num--;
        } else if (rentInfo.getVehicleType() == 2) {
            current_car_rented_num--;
        }

        //测试
        System.out.println("+++还车信息" + rentInfo);
        System.out.println("+++费用：" + userCountPrice(rentInfo));
        System.out.println("+++余额：" + money);
        //将列表中的租车信息删除
        currentRentInfo.remove(rentInfo);
        return true;
    }


    //计算费用
    public float userCountPrice(RentInfo rentInfo){
        //获取当前登入用户信息
        User user = rentInfo.getUser();
        //定义变量
        long distance = caculateDistance(rentInfo);
        int rentType = rentInfo.getVehicleType();
        float totalMoney = 0.0f;
        //计算总价钱
        totalMoney = user.caculateMoney(rentType, distance,bicyclePrice,carPrice);
        return totalMoney;
    }

    //计算行驶距离
    public long caculateDistance(RentInfo rentInfo){
        long distance = rentInfo.getEndPosition() - rentInfo.getStartPosition();
        return distance;
    }




}
