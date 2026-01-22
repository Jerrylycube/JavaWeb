public class Worker extends Person {
        private String unit;
        private int workAge;

        
        public Worker() {
            super();
        }
        public Worker(String age, String gender, String name, String nationality, String unit, int workAge) {
            super(age, gender, name, nationality);
            this.unit = unit;
            this.workAge = workAge;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getWorkAge() {
            return workAge;
        }

        public void setWorkAge(int workAge) {
            this.workAge = workAge;
        }

        public void work() {
            System.out.println("¸Ç·¿×Ó");
        }
    }
