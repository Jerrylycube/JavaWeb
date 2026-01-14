package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ChatRoomSendUDP {
    public static void main(String[] args) throws IOException {
        //快递公司
        DatagramSocket ds = new DatagramSocket();
        Scanner input =new Scanner(System.in);
        while(true){
            System.out.println("请输入要发送内容，输入886结束");
            String str = input.nextLine();

            byte[] b  = str.getBytes();
            InetAddress address = InetAddress.getByName("127.0.0.1");
            DatagramPacket dp =new DatagramPacket(b, b.length,address,1111);
            ds.send(dp);
            if(str.equals("886"))break;
        }
    }
}
