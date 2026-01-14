package TCP.Practice4;

import java.io.*;
import java.net.Socket;

public class Send {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1", 1111);
        BufferedOutputStream bw = new BufferedOutputStream(s.getOutputStream());
        BufferedInputStream br = new BufferedInputStream(new FileInputStream("client\\1744774121920.png"));
        int b;
        byte[] n = new byte[2048];
        while ((b = br.read(n)) != -1) {
            bw.write(n, 0, b);
        }
        bw.flush();//此处要刷新缓冲区！！

        s.shutdownOutput();

        BufferedReader br1 = new BufferedReader(new InputStreamReader(s.getInputStream()));
        while ((b = br1.read()) != -1) {
            System.out.println((char) b);
        }
        br.close();
        s.close();
    }
}
