package citylink.charpter01;

public class VIPUser extends User{
    //
    private String registedDate ;

    public String getRegistedDate() {
        return registedDate;
    }
    public void setRegistedDate(String registedDate) {
        this.registedDate = registedDate;
    }

    @Override
    public void printUserInfo() {
        System.out.println("--------------");
        System.out.println("[用户名]：" + getName());
        System.out.println("[密码]：" + getPassword());
        System.out.println("[金额]：" + getAccountmoney());
        System.out.println("[VIP使用期限]：");
        System.out.println("--------------");
    }

    @Override
    public float caculateMoney(int rentType, long distance, float bicyclePrice, float carPrice) {
        System.out.println("=== VIP用户计费===");
        float totalMoney =  super.caculateMoney(rentType, distance, bicyclePrice, carPrice);
        totalMoney = (float) (totalMoney * 0.8);
        return totalMoney;
    }
}
