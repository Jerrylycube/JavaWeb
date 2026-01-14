import java.util.HashMap;
import java.util.Map;

public class MapDemo2 {
    public static void main(String[] args) {
        Map<String, String> m = new HashMap<>();
        m.put("111", "111");
        m.put("222", "222");
        m.put("333", "333");

//        Set<String> s = m.keySet();
//        for (String key : s) {
//            String v = m.get(key);
//            System.out.println(key + "=" + v);
//        }
//        s.forEach(s3 -> {
//                    String v3 = m.get(s3);
//                    System.out.println(s3 + "=" + v3);
//                }
//        );
//        Set<Map.Entry<String, String>> entries = m.entrySet();
//        for (Map.Entry<String, String> entry : entries) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            System.out.println(key + value);
//        }
//
//        entries.forEach(entry2 -> {
//            String key2 = entry2.getKey();
//            String value2 = entry2.getValue();
//            System.out.println(key2 + value2);
//        });

        m.forEach((key, value) -> System.out.println(key + value));
    }
}