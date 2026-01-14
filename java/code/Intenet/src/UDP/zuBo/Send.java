package UDP.zuBo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Send
{
    public static void main(String[] args)  throws IOException {
        //快递公司
        MulticastSocket ds = new MulticastSocket();
        //包裹
        String s = "你好呀";
        byte[] b  = s.getBytes();
        //组播地址
        InetAddress address = InetAddress.getByName("255.255.255.255");
        DatagramPacket dp =new DatagramPacket(b, b.length,address,1111);
        ds.send(dp);

        ds.close();
    }
}
