package System.studentSystem;

public class StudentUser extends User{
    private String schoolName;
    private int numCode;


    //get/set
    public String getSchoolName() {
        return schoolName;
    }
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    public int getNumCode() {
        return numCode;
    }
    public void setNumCode(int numCode) {
        this.numCode = numCode;
    }


    //构造函数
    public StudentUser(){}


    //打印用户信息
    @Override
    public void printInfo() {
        System.out.println("----------------");
        System.out.println("学号：" + getNumCode());
        System.out.println("用户名：" + getName());
        System.out.println("密码：" + getPassWord());
        System.out.println("学校：" + getSchoolName());
        System.out.println("----------------");

    }
}
