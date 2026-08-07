package System.studentSystem;

public abstract class   User {

    private String name = "admin";
    private String passWord = "123456";
    private int userType;

    //
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public int getUserType() {
        return userType;
    }
    public void setUserType(int userType) {
        this.userType = userType;
    }

    //构造函数
    public User(){}
    public User(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
    }



    //抽象方法
    public abstract void printInfo();




}
