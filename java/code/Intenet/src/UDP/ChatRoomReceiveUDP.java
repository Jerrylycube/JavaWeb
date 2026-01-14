package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ChatRoomReceiveUDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds =new DatagramSocket(1111);
        while(true){
            //将快递接收
            byte[] b = new byte[1024];
            DatagramPacket dp = new DatagramPacket(b,b.length);
            ds.receive(dp);

            //数据解析
            //此时dp数据包，就包含了发送端发送的数据，发送端的那个随机端口，发送端的主机名
            //发送端指定的端口是指定 发送到接收端的那个端口
            byte[]br=dp.getData();
            int length = dp.getLength();
            String receive = new String(br,0,length);
            System.out.println(receive);
            if(receive.equals("886"))break;
        }
        ds.close();
    }
}
