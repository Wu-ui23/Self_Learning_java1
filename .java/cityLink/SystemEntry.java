package citylink.charpter01;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class SystemEntry {           //业务逻辑类

    private CityLinkPlatform platform = null;               //创建类对象


    private final int USER_KEEP_TYPE = 1;


    //构造函数
    public SystemEntry() throws IOException {
        //平台初始化
        System.out.println("平台初始化");
        this.platform = new CityLinkPlatform(USER_KEEP_TYPE);
    }



    //用户注册
    public boolean userRegist() throws IOException {
        //让用户选择注册用户类别
        Scanner scanner = new Scanner(System.in);
        System.out.println("+++系统支持的用户类型有：");
        System.out.println("[1] 学生用户：");
        System.out.println("[2] VIP用户：");
        System.out.println("+++请选择用户类型：");
        int type = scanner.nextInt();
        switch (type){
            case 1:
                registStudentUser();
                break;

            case 2:
                registedVIPUser();
                break;

            default:
                System.out.println("输入错入，请重新输入");
                break;
        }
        return true;
    }


    //注册学生用户
    public boolean registStudentUser() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("+++请输入您的用户名：");
        String userName = scanner.next();
        System.out.print("+++请输入您的密码：");
        String inputPsw = scanner.next();
        System.out.print("+++请输入金额：");
        float money = scanner.nextFloat();
        System.out.print("+++请输入您的学校：");
        String school = scanner.next();

        //打包成一个整体对象
        StudentUser student = new StudentUser();
        student.setName(userName);
        student.setPassword(inputPsw);
        student.setAccountmoney(money);
        student.setSchoolNmae(school);
        student.setUserType(1);
        //添加到平台，保存到内存中
        platform.addUser(student);
        platform.writerUserInfo(student);
        return true;
    }


    //注册VIP用户
        public boolean registedVIPUser() throws IOException {
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入用户名：");
            String VIPName = scanner.next();
            System.out.print("请输入密码：");
            String VIPPsw = scanner.next();
            System.out.println("请输入金额：");
            float money = scanner.nextFloat();
            //注册日期
            LocalDate localDate = LocalDate.now();  //该类为final类，不能继承，可以实例化，但是该类构造函数被private修饰则不能被外界访问实例化。

            VIPUser vipUser = new VIPUser();
            vipUser.setName(VIPName);
            vipUser.setPassword(VIPPsw);
            vipUser.setAccountmoney(money);
            vipUser.setRegistedDate(localDate.toString()); //所有类对象都可以转为字符串
            vipUser.setUserType(2);
            platform.addUser(vipUser);
            platform.writerUserInfo(vipUser);

            return true;
        }



        //租车
        public boolean rentVehicle(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("+++请输入租车类别：");
            System.out.println("[1] 共享单车");
            System.out.println("[2] 共享汽车");
            System.out.print("+++请输入：");
            int type = scanner.nextInt();
            //向平台发起租车流程
            platform.userRentVehicle(type);

        return true;
        }



    //登入账户，输入密码
    public boolean verifyUser() {
        System.out.println("=== CityLink 1.0 ===");

        Scanner scanner = new Scanner(java.lang.System.in);

        while (true) {
            System.out.print("+++请输入您的用户名：");
            String username = scanner.next();
            System.out.print("+++请输入用户密码：");
            String inputPwd = scanner.next();
            boolean result = false;

            //for循环是用来寻找匹配项
            for (int i = 0; i < platform.getIndex(); ++i) {
                //获取该列表，再进行字符串比较
                User adminUser = platform.getRegistedUsers().get(i);
                //字符串比较
                if (username.equals(adminUser.getName()) && inputPwd.equals(adminUser.getPassword())) {
                    //标记当前用户
                    platform.setCurrentLoginUser(adminUser);
                    System.out.println("验证成功！");
//                    System.out.println("***当前用户为：" + username);
                    result = true;
                    break;
                }
            }
            //for循环比较完之后，确认没有匹配项，再打印输入错误
            if(!result){
                System.out.println("输入错误，请重新输入！");
            }
            return result;                                                                                                        // 把盒子里的值拿出去
        }
    }


    //
    public void systemMenu() throws IOException {
       outer: while (true){
            boolean result = verifyUser();             //调用该函数并接受返回值
            if(result){

                inner: while (true) {                   //给while设置标签
                    printMenu();
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("+++请输入你的选项：");
                    int num = scanner.nextInt();

                    switch (num) {
                        case 0:
                            //退出系统
                            System.out.println("确认退出！");
                            break outer;         // 用标签退出while
                        case 1:
                            //用户租车
                            rentVehicle();
                            break ;
                        case 2:
                            //用户中心
                            platform.printAllUser();
                            break;
                        case 3:
                            //用户还车
                            platform.userBackVehicle();
                            break ;
                        case 4:
                            //用户注册
                            userRegist();
                            break ;
                        case 5:
                            //切换用户
                            System.out.println("退出当前用户！");
                            break inner;
                        default:
                            System.out.println("输入非法数字，请重新输入");
                            break ;
                    }

                }

            }else {
                break ;
            }
        }
    }


    public void printMenu(){
        System.out.println("=== 系统菜单 ===");
        System.out.println("当前用户为：" + platform.getCurrentLoginUser().getName());
        System.out.println("1. 用户租车");
        System.out.println("2. 用户中心");
        System.out.println("3. 用户还车");
        System.out.println("4. 用户注册");
        System.out.println("5. 切换用户");
        System.out.println("0. 退出");
        System.out.println("=== ======= ===");
        System.out.println();
    }



}
