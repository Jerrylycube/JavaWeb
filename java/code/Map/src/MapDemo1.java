import java.util.HashMap;
import java.util.Map;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class MapDemo1 {
    public static void main(String[] args) {
        Map<String,String> m=new HashMap<>();
        m.put("111","111");
        m.put("222","111");
        m.put("333","111");

        String s=m.put("111","222");
        System.out.println(s);
        System.out.println(m);

        System.out.println(m.containsKey("111"));
        System.out.println(m.remove("111"));
        System.out.println(m.containsKey("111"));
        System.out.println(m.containsValue("22"));
        System.out.println(m.size());
        m.clear();

        System.out.println(m);
    }
}