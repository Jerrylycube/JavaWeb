import java.io.*;
import java.util.HashMap;

public class FileCsb {
    public static void main(String[] args) throws IOException {
        File f = new File("File\\a\\b.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        String len;
        HashMap<Integer, String> list = new HashMap<>();
        while ((len = br.readLine()) != null) {
            System.out.println(len);
            String[] str = len.split("\\.");
            //System.out.println(Arrays.toString(str));

            list.put(Integer.parseInt(str[0]), str[1]);
        }
        br.close();
        File f2 = new File("File\\b.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f2));
        list.forEach((key, value) -> {
            try {
                bw.write(value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        bw.close();
    }
}
