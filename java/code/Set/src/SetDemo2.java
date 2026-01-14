import java.util.TreeSet;

public class SetDemo2 {
    public static void main(String[] args) {
        TreeSet<String> s = new TreeSet<>((o1, o2) -> {
            int i = o1.length() - o2.length();
            i = i == 0 ? o1.compareTo(o2) : i;
            return i;
        });
        s.add("a");
        s.add("b");
        s.add("cdd");
        s.add("abc");
        for (String string : s) {
            System.out.println(string);
        }
    }
}
