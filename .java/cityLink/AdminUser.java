package citylink.charpter01;

public class AdminUser extends User{

    @Override
    public void printUserInfo() {
        System.out.println("--------------");
        System.out.println("[用户名]：" + getName());
        System.out.println("[密码]：" + getPassword());
        System.out.println("[金额]：" + getAccountmoney());
        System.out.println("--------------");
    }
}
