package citylink.charpter01;

import java.io.IOException;
import java.util.ArrayList;

public interface UserDataOpen {
    public ArrayList<User> readUserDatas() throws IOException;
    public boolean writeUserData(User user);

}
