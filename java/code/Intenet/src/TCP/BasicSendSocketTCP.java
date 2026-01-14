package TCP;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class BasicSendSocketTCP {
    public static void main(String[] args) throws IOException {
        Socket s =new Socket("127.0.0.1",1111);//此处是三次握手协议，保证连接
        OutputStream os = s.getOutputStream();

        os.write("你好呀".getBytes());
        s.close();//此处要有四次挥手协议，保证IO流通道内容已经全部进行完
    }
}
