package citylink.charpter01;

public class StudentUser extends  User{
    //保存学生学校信息
    private String schoolNmae;

    //
    public String getSchoolNmae() {
        return schoolNmae;
    }
    public void setSchoolNmae(String schoolNmae) {
        this.schoolNmae = schoolNmae;
    }

    @Override
    public void printUserInfo() {
        System.out.println("--------------");
        System.out.println("[用户名]：" + getName());
        System.out.println("[密码]：" + getPassword());
        System.out.println("[金额]：" + getAccountmoney());
        System.out.println("[学校]：" + getSchoolNmae());
        System.out.println("--------------");
    }


    @Override
    public float caculateMoney(int rentType, long distance, float bicyclePrice, float carPrice) {
        System.out.println("===学生计费===");
        float totalMoney =  super.caculateMoney(rentType, distance, bicyclePrice, carPrice);
        totalMoney -= 2;
        return totalMoney;
    }




}
