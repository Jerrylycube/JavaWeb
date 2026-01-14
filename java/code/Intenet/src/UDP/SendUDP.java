package UDP;

import java.io.IOException;
import java.net.*;

public class SendUDP {
    public static void main(String[] args) throws IOException {
        //快递公司
        DatagramSocket ds = new DatagramSocket();
        //包裹
        String s = "你好呀";
        byte[] b  = s.getBytes();
        InetAddress address = InetAddress.getByName("127.0.0.1");
        DatagramPacket dp =new DatagramPacket(b, b.length,address,1111);
        ds.send(dp);

        ds.close();
    }
}
