package System.studentSystem;

public class Information {
    //学号
    private int numCode;
    private String name;
    private String gender;
    private int age;
    private String classRoom;
    private String grade;
    private float score;


    //get/set
    public float getScore() {
        return score;
    }
    public void setScore(float score) {
        this.score = score;
    }
    public int getNumCode() {
        return numCode;
    }
    public void setNumCode(int numCode) {
        this.numCode = numCode;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getClassRoom() {
        return classRoom;
    }
    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    //构造函数
    public Information(){}
    public Information(int numCode, String name, String grade, float score) {
        this.numCode = numCode;
        this.name = name;
        this.grade = grade;
        this.score = score;
    }

    //打印信息
    public void printStudentInfo(){
        System.out.println("--------------------");
        System.out.println("年级：" + grade);
        System.out.println("班级：" + classRoom);
        System.out.println("学号：" + numCode);
        System.out.println("姓名：" + name);
        System.out.println("分数：" + score);
        System.out.println("--------------------");
    }


}
