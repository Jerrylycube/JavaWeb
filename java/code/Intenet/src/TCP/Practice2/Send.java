package TCP.Practice2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Send {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1", 1111);//此处是三次握手协议，保证连接
        OutputStream os = s.getOutputStream();
        os.write("你好呀".getBytes());
        s.shutdownOutput();//此处要关闭输出流再进行读取

        InputStreamReader ir = new InputStreamReader(s.getInputStream());
        //将字节流，转换成字符流，再转换成高级的字符缓冲流
        //这样就可以读取中文了，不然字节流一个一个字节进行转换成char，就会乱码
        int b;
        while ((b = ir.read()) != -1) {
            System.out.println((char) b);
        }


        s.close();//此处要有四次挥手协议，保证IO流通道内容已经全部进行完
    }
}
