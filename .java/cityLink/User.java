package citylink.charpter01;

public abstract class User {
    private String name = null;
    private String password = null;
    private float accountmoney = 0;
    private int userType = 0;


    //get/set
    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setAccountmoney(float accountmoney) {
        this.accountmoney = accountmoney;
    }
    public float getAccountmoney() {
        return accountmoney;
    }

    public int getUserType() {
        return userType;
    }
    public void setUserType(int userType) {
        this.userType = userType;
    }

    //抽象方法
    public abstract void printUserInfo() ;


    //计费逻辑
    public float caculateMoney(int rentType,long distance, float bicyclePrice, float carPrice){
        float totalMoney = 0.0f;
        if(rentType == 1){
            totalMoney = distance/1000 * bicyclePrice;

        }else if(rentType == 2){
            totalMoney = distance/1000 * carPrice;
        }
        return totalMoney;
    }

    //


    @Override
    public String toString() {
        return name + " " + password + " " + accountmoney + " " + userType;
    }
}
