package System.studentSystem;

public class AdminUser extends User{



    @Override
    public void printInfo() {
        System.out.println("----------------");
        System.out.println("用户名：" + getName());
        System.out.println("密码：" + getPassWord());
        System.out.println("----------------");

    }




    //


}


