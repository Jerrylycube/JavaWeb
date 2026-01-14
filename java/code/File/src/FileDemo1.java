import java.io.*;
import java.util.Arrays;

//TIP 要<b.txt>运行</b.txt>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class FileDemo1 {
    public static void main(String[] args) throws IOException {
        File f = new File("File\\a");
        File fc = new File(f, "\\a.txt");
        File fc2 = new File(f, "\\b.txt.txt");
        FileWriter fw = new FileWriter(fc);
        OutputStream fd = new FileOutputStream(fc2);
        fw.write("123cfdsdadwa\r\nasda,你好");//字节流拷贝的话，不会乱码
        fw.close();//一个文件不能同时开俩个通道！
        FileInputStream fr = new FileInputStream((fc));
        byte[] readerBuffer = new byte[2048];
        int len;
        while ((len = fr.read(readerBuffer)) != -1) {
            //System.out.println(len);
            String str = new String(readerBuffer, 0, len);
            System.out.println(str);
            System.out.println(Arrays.toString(readerBuffer));
            fd.write(readerBuffer, 0, len);
        }
        fr.close();
        fd.close();


    }
}