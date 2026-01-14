public class Student2 implements Comparable<Student2> {
    private String name;
    private int age;
    private double chineseGrade;
    private double mathGrade;
    private double englishGrade;

    public Student2(String name, int age, double chineseGrade, double mathGrade, double englishGrade) {
        this.name = name;
        this.age = age;
        this.chineseGrade = chineseGrade;
        this.mathGrade = mathGrade;
        this.englishGrade = englishGrade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getChineseGrade() {
        return chineseGrade;
    }

    public void setChineseGrade(double chineseGrade) {
        this.chineseGrade = chineseGrade;
    }

    public double getMathGrade() {
        return mathGrade;
    }

    public void setMathGrade(double mathGrade) {
        this.mathGrade = mathGrade;
    }

    public double getEnglishGrade() {
        return englishGrade;
    }

    public void setEnglishGrade(double englishGrade) {
        this.englishGrade = englishGrade;
    }

    @Override
    public String toString() {
        double sum = chineseGrade + mathGrade + englishGrade;
        return "Student2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ",sum=" + sum +
                ", chineseGrade=" + chineseGrade +
                ", mathGrade=" + mathGrade +
                ", englishGrade=" + englishGrade +
                '}';
    }


    @Override
    public int compareTo(Student2 o) {
        double allGrade = this.chineseGrade + this.englishGrade + this.mathGrade;
        double allGradeO = o.chineseGrade + o.englishGrade + o.mathGrade;
        double i = allGrade - allGradeO;
        i = i == 0 ? this.chineseGrade - o.chineseGrade : i;
        i = i == 0 ? this.mathGrade - o.mathGrade : i;
        i = i == 0 ? this.englishGrade - o.englishGrade : i;
        i = i == 0 ? this.age - o.age : i;
        i = i == 0 ? this.name.compareTo(o.name) : i;
        if (i > 0) return 1;
        else if (i < 0) return -1;
        else return 0;
    }
}
