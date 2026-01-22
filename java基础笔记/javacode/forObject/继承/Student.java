 public class Student extends Person {
        private String school;
        private String stuNumber;

        public Student()
        {
            super();
        }
        public Student(String age, String gender, String name, String nationality, String school, String stuNumber) {
            super(age, gender, name, nationality);
            this.school = school;
            this.stuNumber = stuNumber;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getStuNumber() {
            return stuNumber;
        }

        public void setStuNumber(String stuNumber) {
            this.stuNumber = stuNumber;
        }

        public  void work() {
            System.out.println("学习");
        }

    }
