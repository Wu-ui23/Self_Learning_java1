package citylink.charpter01;

import java.util.ArrayList;

public class UserDBOPen implements UserDataOpen{
    @Override
    public boolean writeUserData(User user) {
        return false;
    }

    @Override
    public ArrayList<User> readUserDatas() {
        return null;
    }
}
