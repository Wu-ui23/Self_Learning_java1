package citylink.charpter01;


import java.io.IOException;

public class CityLinkMain {

    /*
    系统入口

    main调用systementry，systementry再调用platform
     */
    public static void main(String[] args) throws IOException {

   SystemEntry entry = new SystemEntry();
        entry.systemMenu();

    }
}
