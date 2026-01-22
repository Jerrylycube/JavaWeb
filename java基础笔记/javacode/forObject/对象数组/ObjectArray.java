public class ObjectArray{
    private int id;
    private String name;
    private int money;
    private int num;
    
    public ObjectArray(int id, String name, int money, int num) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.num = num;
    }

    public ObjectArray() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void showInfo()
    {
        System.out.println(this.id+" "+this.money+" "+this.name+" "+this.num);
    }
    

    
}