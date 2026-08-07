package citylink.charpter01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UserFileOpen implements UserDataOpen{

    ArrayList<User> allUsers = new ArrayList<>();

    @Override
    public ArrayList<User> readUserDatas() throws IOException {
        //获取内路径                      类名. class. getClassLoader()        --->    获取内路径
        ClassLoader classLoader = UserFileOpen.class.getClassLoader();
        //创建对象，打开文件
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        classLoader.getResourceAsStream("user.txt")
                )
        );
        //读取文件
        String line;
        while ((line = reader.readLine()) != null){
            //解析每一行代码
            String[] parts = line.split(" ");//字符串分割
            int type = Integer.parseInt(parts[3]);//
            User user = null;
            if(1 == type){
                user = new StudentUser();
            } else if (2 == type) {
                user = new VIPUser();
            }

            if (user != null) {
                user.setName(parts[0]);
                user.setPassword(parts[1]);
                user.setAccountmoney(Float.parseFloat(parts[2]));
                allUsers.add(user);
            }
        }

        if(reader != null){
            reader.close();
        }
        return allUsers;
    }

    @Override
    public boolean writeUserData(User user) {
        return false;
    }
}
