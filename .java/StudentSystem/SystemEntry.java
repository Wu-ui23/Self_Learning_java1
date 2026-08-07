package System.studentSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SystemEntry {
    SystemPlatform platform = null;

    /*
        +++调用另一个类中的非静态方法，必须实例化
        也可以直接SystemPlatform platform = new SystemPlatform（）；
     */

    public SystemEntry(){
        System.out.println("+++平台初始化");
        System.out.println("欢迎进入学生信息管理系统~");
        this.platform = new SystemPlatform();
    }



    //验证用户
    public int verifyUser() throws SQLException {
        System.out.println("===Student Information System!===");
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int userType = 0;
        // (!exit) == true      -->     ()中为true继续运行，为false 退出循环       -->       （）中为继续循环的条件
        while (!exit) {
            System.out.println("请输入您的用户名和密码：");
            System.out.print("+++用户名：");
            String userName = scanner.next();
            System.out.print("+++密码：");
            String userPassword = scanner.next();

            //要干什么
            String sql = "select name, password, userType from user_info where name = ? and password = ?";
            //连接数据库
            Connection connection = DBUser.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            //填充参数
            pstmt.setString(1,userName);
            pstmt.setString(2,userPassword);
            //在数据库中查找比对
            ResultSet result = pstmt.executeQuery();
            //对比成功之后进入
            if(result.next()){
                userType = result.getInt("userType");//填入数据库中的字段名
                System.out.println("***登入成功！");
                platform.setCurrentUser(result.getString("name"));
                exit = true;
            }else {
                System.out.println("***用户名或密码错误！");
            }
        }
        return userType;
    }


    //选择
    public boolean choiceEntry() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        //括号内为循环条件  -->  即（！exit）表示括号内为false 就一直循环
        outer:
        while (!exit) {
            //返回数字，记录用户类型
            int result = verifyUser();
            if (result == 1) {
                inner:
                while (true) {
                    //打印选项目录
                    printMenu();
                    System.out.print("+++请输入你的选择：");
                    int num1 = scanner.nextInt();

                    switch (num1) {
                        case 0:
                            //退出当前用户
                            break outer;
                        case 1:
                            //增加
                            platform.addStudent();
                            break;
                        case 2:
                            //删除
                            platform.delStudent();
                            break;
                        case 3:
                            //修改
                            platform.modifyStudent();
                            break;
                        case 4:
                            //查询
                            checkStudent();
                            break;
                    }
                }
            } else if (result == 2) {
                while (true) {
                    printStudentMenu();
                    System.out.print("+++请输入你的选择：");
                    int num2 = scanner.nextInt();
                    switch (num2) {
                        case 0:
                            //退出当前用户
                            break outer;
                        case 1:
                            //查询学生信息
                            platform.checkStudentInfo();
                            break;
                    }
                }
            }
        }
        return true;
    }

    //学生选项目录
    public void printStudentMenu(){
        System.out.println("--------------------");
        System.out.println("当前用户为：" + platform.getCurrentUser());

        System.out.println("0.退出当前用户");
        System.out.println("1.查询学生信息");
        System.out.println("--------------------");
    }


    //选项目录
    public void printMenu(){
        System.out.println("--------------------");
        System.out.println("当前用户为：" + platform.getCurrentUser());
        System.out.println("0.退出当前用户");
        System.out.println("1.增加学生信息");
        System.out.println("2.删除学生信息");
        System.out.println("3.修改学生信息");
        System.out.println("4.查询学生信息");
        System.out.println("--------------------");
    }


    //注册账号 or 登入系统
    public boolean firstChoice() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        // !flag == true     -->     括号内为循环条件，即括号内为
        outer:
        while (!flag) {
            //打印菜单
            register_or_login();
            System.out.print("+++请选择：");
            int num = scanner.nextInt();
            switch (num) {
                case 0:
                    //退出系统
                    flag = true;
                    break;
                case 1:
                    //注册账号
                    registerUser();
                    break;
                case 2:
                    //登入系统
                    choiceEntry();
                    break ;
                default:
                    System.out.println("输入错误，请重新输入！");
                    break;
            }
        }
        return flag;
    }

    //注册用户
    public boolean registerUser() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        outer:
        while (true) {
            System.out.println("1.老师用户");
            System.out.println("2.学生用户");
            System.out.print("+++请选择你要注册的用户类型：");
            int num = scanner.nextInt();
            switch (num) {
                case 1:
                    //老师用户     标记类型
                    registerTeacher(num);
                    break outer;
                case 2:
                    //学生用户
                    registerStudent(num);
                    break outer;
                default:
                    System.out.println("输入错误，请重新选择！");
                    break;
            }
        }
        return true;
    }

    //注册老师用户
    public boolean registerTeacher(int userType) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("+++请输入您的用户名：");
        String name = scanner.next();
        System.out.print("+++请输入您的密码：");
        String password = scanner.next();

        String sql = "insert into user_info(name, password,school,userType) values(?,?,?,?)";
        Connection connection = DBUser.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,password);
        pstmt.setString(3," ");
        pstmt.setInt(4,userType);
        //执行到MySQL
        int line = pstmt.executeUpdate();
        //测试
        System.out.println(line > 0 ? "***注册成功！" : "***注册失败！");

        return true;
    }

    //注册学生用户
    public boolean registerStudent(int userType) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("+++请输入您的用户名：");
        String name = scanner.next();
        System.out.print("+++请输入您的密码：");
        String password = scanner.next();
        System.out.print("+++请输入您的学校名称：");
        String school = scanner.next();
        //
        String sql = "insert into user_info (name,password, school, userType) values(?,?,?,?)";
        //
        Connection connection = DBUser.getConnection();
        //
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,password);
        pstmt.setString(3,school);
        pstmt.setInt(4,userType);
        //把SQL发给MySQL执行,在数据库中插入数据
        int line = pstmt.executeUpdate();
        if(line > 0){
            System.out.println("***注册成功！");
        }else {
            System.out.println("***注册失败！");
        }
        /*
        三元运算符    if-else 的简写版       ---->       条件 ？ 值1 ：值2
            System.out.println(line > 0 ? "注册成功！" : "注册失败！");
         */

        return true;
    }


    //目录
    public void register_or_login(){
        System.out.println("======'System Entry'======");
        System.out.println("0.退出系统");
        System.out.println("1.注册账户");
        System.out.println("2.登入用户");
        System.out.println("===========================");
    }



    //查询
    public boolean checkStudent() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        outer:
        while (true){
            System.out.println("----------------------");
            System.out.println("0.退出查询");
            System.out.println("1.查询指定学生信息");
            System.out.println("2.查询所有学生信息");
            System.out.println("----------------------");
            System.out.print("+++请输入您的选项：");
            int num = scanner.nextInt();
            switch (num){
                case 0:
                    break outer;
                case 1:
                    //查询指定学生
                    platform.checkStudentInfo();
                    break;
                case 2:
                    //查询所有学生
                    platform.checkAllStudentInfo();
                    break ;
            }
        }
        return true;
    }



}
