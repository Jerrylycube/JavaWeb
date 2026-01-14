package TCP.Practice2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Receive {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1111);
        Socket accept = ss.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        //将字节流，转换成字符流，再转换成高级的字符缓冲流
        //这样就可以读取中文了，不然字节流一个一个字节进行转换成char，就会乱码
        int b;
        char[] n = new char[1024];//此处再在内存进行一次性多个的搬运
        StringBuilder sb = new StringBuilder();
        while ((b = br.read(n)) != -1) {
            sb.append(new String(n, 0, b));
        }
        System.out.println(sb);

//回写给客户端
        OutputStream os = accept.getOutputStream();
        os.write("有多好".getBytes());

        os.close();
        ss.close();
    }
}
