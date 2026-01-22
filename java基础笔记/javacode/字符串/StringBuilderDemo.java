public class StringBuilderDemo {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        // sb.append("aaa");
        // sb.append("bbb");
        // sb.append("ccc");
        sb.append("aaa").append("ccc").append("ddd");
        sb.reverse();
        String str = sb.toString();
        System.out.println(sb);
        System.out.println(str);        
    }
}