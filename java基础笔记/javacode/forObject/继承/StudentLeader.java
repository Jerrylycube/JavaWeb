public class StudentLeader extends Student {
    private String job;

        public StudentLeader() {
            //super();
        }

        
        public StudentLeader(String age, String gender, String name, String nationality, String school,
                String stuNumber, String job) {
            super(age, gender, name, nationality, school, stuNumber);
            this.job = job;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public void meeting() {
            System.out.println("¿ª»á");
        }

    }