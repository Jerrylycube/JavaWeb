package TCP.Practice1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Receive {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1111);
        Socket accept = ss.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        //InputStream is = accept.getInputStream();
        //InputStreamReader ir = new InputStreamReader(is);
        int b;
        char[] n = new char[1024];

        while ((b = br.read(n)) != -1) {
            StringBuilder sb = new StringBuilder();
            System.out.println("进入");
            sb.append(new String(n, 0, b));
            System.out.println(sb);
        }
        //System.out.println(sb);
        System.out.println("退出");
        //System.out.println((char) b);

        accept.close();
        ss.close();
    }
}
