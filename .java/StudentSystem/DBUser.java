package System.studentSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUser{
    //                                                 "jdbc:mysql://localhost：3306/数据库名称？useSSL = false&characterEncoding = utf8";
    private static final String URL = "jdbc:mysql://localhost:3306/javaSystem?useSSL = false&characterEncoding = utf8";
    private static final String username = "root";//用户名
    private static final String password = "123456@Ab";//密码

    //提供连接MySQL的方法
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,username,password);
    }


        /*
        1.静态方法只能直接访问静态变量      &&        非静态方法可以访问静态变量和实例变量
        2.final 变量 == 常量  --->    可以被继承
        3.final 方法   子类不能重写
        4.final 类    不能被继承

     */


}
