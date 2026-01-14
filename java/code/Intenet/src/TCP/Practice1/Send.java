package TCP.Practice1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Send {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1", 1111);
        OutputStream os = s.getOutputStream();
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("请输入要发送内容");
            String str = input.nextLine();
            if (str.equals("886")) break;
            os.write(str.getBytes());
        }
        //os.close();
        s.close();
    }
}
