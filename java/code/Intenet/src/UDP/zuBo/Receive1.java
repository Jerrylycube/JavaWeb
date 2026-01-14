package UDP.zuBo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receive1 {
    public static void main(String[] args) throws IOException {
        //找快递公司，端口号要选定发送端的那个，即发送端发到我们哪里了
        MulticastSocket ds =new MulticastSocket(1111);
        InetAddress address = InetAddress.getByName("224.0.0.1");
        ds.joinGroup(address);
        //将快递接收
        byte[] b = new byte[1024];
        DatagramPacket dp = new DatagramPacket(b,b.length);
        ds.receive(dp);

        //数据解析
        //此时dp数据包，就包含了发送端发送的数据，发送端的那个随机端口，发送端的主机名
        //发送端指定的端口是指定 发送到接收端的那个端口
        System.out.println(dp.getAddress());
        System.out.println(dp.getPort());
        byte[]br=dp.getData();
        int length = dp.getLength();
        System.out.println(new String(br,0,length));

        ds.close();
    }
}
