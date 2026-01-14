package TCP.Pratice3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Receive {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1111);
        Socket accept = ss.accept();

        BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("serever\\1744774121920.png"));
        BufferedInputStream br = new BufferedInputStream(accept.getInputStream());
        int b;
        byte[] n = new byte[1024];
        while ((b = br.read(n)) != -1) {
            bw.write(n, 0, b);
            System.out.println("nh");
        }

        //回写
        OutputStreamWriter bsw = new OutputStreamWriter(accept.getOutputStream());
        bsw.write("好了");

        bw.flush();
        bw.close();
        bsw.close();
        accept.close();
        ss.close();
    }
}
