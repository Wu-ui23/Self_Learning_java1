package System.studentSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SystemPlatform {
    //当前登入用户
    private String currentUser;

    //get/set
    public String  getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    //构造方法
    public SystemPlatform(){
        User adminUser = new AdminUser();
    }


    //查询指定学生信息
    public boolean checkStudentInfo() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入您的学号：");
        int num = scanner.nextInt();
        System.out.print("请输入您的姓名：");
        String name = scanner.next();

        //在学生信息列表中，寻找匹配项
        String sql = "select * from student_info where id = ? and name = ?";
        Connection connection = DBInformation.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,num);
        pstmt.setString(2,name);
        //查询
        ResultSet result = pstmt.executeQuery();
        //打印
        if(result.next()){
            String grade = result.getString("grade");
            String classroom = result.getString("classroom");
            int numCode = result.getInt("id");
            String username = result.getString("name");
            float score = result.getFloat("score");
            //
            Information info = new Information();
            info.setScore(score);
            info.setGrade(grade);
            info.setName(username);
            info.setNumCode(numCode);
            info.setClassRoom(classroom);
            info.printStudentInfo();
        }else {
            System.out.println("***没有该学生信息");
        }
        return true;
    }


    //查询所有学生信息
    public boolean checkAllStudentInfo() throws SQLException {
        System.out.println("以下为所有学生的所有信息：");
        //SQL语句
        String sql = "select * from student_info";
        //连接数据库
        Connection connection = DBInformation.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //查询
        ResultSet result = pstmt.executeQuery();
        /*
            在ResultSet和ResutleSetMetaData的列索引是从1开始
         */
        //获取总列数
        ResultSetMetaData meta = result.getMetaData();
        //记录总列数
        int lines = meta.getColumnCount();
        //打印数据
        while (result.next()){
            for (int i = 1; i <= lines; i++) {
                String info = result.getString(i);
                System.out.print(info + "  ");
            }
            System.out.println();
        }
        return true;
    }


    //增加学生信息
    public boolean addStudent() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("+++请输入要添加的学生姓名：");
        String name = scanner.next();
        System.out.print("+++请输入该学生的年级：");
        String grade = scanner.next();
        System.out.print("+++请输入该学生的班级：");
        String classroom = scanner.next();
        System.out.print("+++请输入该学生的学号：");
        int num = scanner.nextInt();
        System.out.print("+++请输入该学生的性别：");
        String gender = scanner.next();
        System.out.print("+++请输入该学生的年龄：");
        int age = scanner.nextInt();
        System.out.print("+++请输入该学生的分数：");
        float score = scanner.nextFloat();

        //定义对数据库做什么操作
        String sql = "insert into student_info (id, name, gender, age, classroom, grade, score) values(?,?,?,?,?,?,?)";
        //获取要连接哪个数据库
        Connection connection = DBInformation.getConnection();
        //创建一个准备执行SQL的对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //设置写入数据库的值
        pstmt.setInt(1,num);
        pstmt.setString(2,name);
        pstmt.setString(3,gender);
        pstmt.setInt(4,age);
        pstmt.setString(5,classroom);
        pstmt.setString(6,grade);
        pstmt.setFloat(7, score);
        //写入数据库
        int line = pstmt.executeUpdate();
        System.out.println(line > 0 ? "***添加成功！" : "***添加失败！");

        return true;
    }


    //删除学生信息
    public boolean delStudent() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        outerLap:
        while (true) {
            checkAllStudentInfo();
            System.out.println("---------------------------------");
            System.out.print("请输入你要删除学生的学号(输入0退出)：");
            int num = scanner.nextInt();
            if(num == 0) break outerLap;

            String sql = "delete from student_info where id = ?";
            Connection connection = DBInformation.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, num);
            //更新数据
            int line = pstmt.executeUpdate();
            System.out.println(line > 0 ? "***删除成功！" : "***没有该学生信息");
        }
        return true;
    }


    //修改学生信息
    public boolean modifyStudent() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        outLap:
        while (true) {
            System.out.print("请输入你要修改学生的学号（输入0退出）：");
            int num = scanner.nextInt();
            scanner.nextLine();
            /*
                    int num = scanner.nextInt(); 一般 nextInt(),nextDouble()等 的next都只会带走< 数字 > 导致 < \n  > 还留在缓冲区，从而不能输入下一行
                    因此 在后面要添加一行 nextLine() ,该next 是在缓冲区看到  < \n  > 即结束，带走 \n 并返回空字符串，则缓冲区没有残留
                    当缓冲区什么都没有的时候，才会进行下一次键盘录入

             */
            if(num == 0) break outLap;
            //
            String sql = "select * from student_info where id = ?";
            Connection connection = DBInformation.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, num);
            ResultSet r = pstmt.executeQuery();

            if (r.next()) {
                System.out.println("找到该学生！信息如下：");
                int numCode = r.getInt("id");
                String name = r.getString("name");
                String gender = r.getString("gender");
                int age = r.getInt("age");
                String classroom = r.getString("classroom");
                String grade = r.getString("grade");
                float score = r.getFloat("score");

                System.out.println("学号：" + numCode);
                System.out.println("姓名：" + name);
                System.out.println("性别：" + gender);
                System.out.println("年龄：" + age);
                System.out.println("班级：" + classroom);
                System.out.println("年级：" + grade);
                System.out.println("分数：" + score);
                System.out.println("==========================");

                System.out.println("请输入新信息(回车表示不修改)：");
                System.out.print("新姓名：");
                String newName = scanner.nextLine();
                if (newName.isEmpty()) newName = name;

                System.out.print("新性别：");
                String newGender = scanner.nextLine();
                if (newGender.isEmpty()) newGender = gender;

                System.out.print("新年龄：");
                String ageInput = scanner.nextLine();
                int newAge = ageInput.isEmpty() ? age : Integer.parseInt(ageInput);

                System.out.print("新班级：");
                String newClassroom = scanner.nextLine();
                if (newClassroom.isEmpty()) newClassroom = classroom;

                System.out.print("新年级：");
                String newGrade = scanner.nextLine();
                if (newGrade.isEmpty()) newGrade = grade;

                System.out.print("新分数：");
                String scoreInput = scanner.nextLine();
                float newScore = scoreInput.isEmpty() ? score : Float.parseFloat(scoreInput);

                System.out.print("确认修改（y/n）：");
                String confirm = scanner.next();
                if (!confirm.equalsIgnoreCase("y")) {
                    System.out.println("取消修改！");
                    return false;
                }

                //确认修改
                String sql2 = "update student_info set name = ?,gender = ?, age = ?, classroom = ?, grade = ?, score = ? where id =?";
                Connection connection2 = DBInformation.getConnection();
                PreparedStatement update = connection2.prepareStatement(sql2);
                //
                update.setString(1, newName);
                update.setString(2, newGender);
                update.setInt(3, newAge);
                update.setString(4, newClassroom);
                update.setString(5, newGrade);
                update.setFloat(6, newScore);
                update.setInt(7, numCode);
                //
                int line = update.executeUpdate();
                System.out.println(line > 0 ? "***修改成功！" : "***修改失败！");

            } else {
                System.out.println("没有找到该学生！");
            }
        }
        return true;
    }



}
