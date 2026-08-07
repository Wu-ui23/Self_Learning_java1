package System.studentSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBInformation {

    private static final String URL = "jdbc:mysql://localhost:3306/javaSystem?useSSL = false&characterEncoding = utf8";
    private static final String username = "root";//用户名
    private static final String password = "123456@Ab";//密码

    //
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,username,password);
    }

}
