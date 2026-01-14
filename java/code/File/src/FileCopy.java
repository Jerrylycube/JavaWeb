import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) throws IOException {
        File src = new File("File");
        File des = new File("Des");

        fileCopy(src, des);


    }

    public static void fileCopy(File src, File des) throws IOException {
        des.mkdirs();
        File[] files = src.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                FileInputStream fs = new FileInputStream(file);
                byte[] flash = new byte[2048];
                int len;
                FileOutputStream fd = new FileOutputStream(new File(des, file.getName()));
                while ((len = fs.read(flash)) != -1) {
                    fd.write(flash, 0, len);
                }
                fd.close();
                fs.close();
            } else {
                fileCopy(file, new File(des, file.getName()));

            }
        }

    }

}
