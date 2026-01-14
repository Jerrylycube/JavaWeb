import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class FileSet {
    public static void main(String[] args) throws IOException {
        File f = new File("File\\a\\a.txt");
        FileReader fis = new FileReader(f);
        char[] c = new char[10];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = fis.read(c)) != -1) {
            String str = new String(c, 0, len);
            sb.append(str);
        }
        fis.close();
        String str = sb.toString();
        Integer[] array = Arrays.stream(sb.toString()
                        .split("-"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        String str2 = Arrays.toString(array).replace(", ", "-");
        String str3 = str2.substring(1, str2.length() - 1);
        System.out.println(str3);
        
//        String[] str1 = str.split("-");
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        for (String s : str1) {
//            list.add(Integer.parseInt(s));
//        }
//        Collections.sort(list);
//        System.out.println(list);
//        FileWriter fw = new FileWriter(f);
//        for (int i = 0; i < list.size(); i++) {
//            if (i == list.size() - 1) {
//                fw.write(list.get(i) + "");
//            } else {
//                fw.write(list.get(i) + "-");
//            }
//        }
//        fw.close();

    }
}
