package TCP.Practice4;

import java.io.IOException;
import java.net.ServerSocket;

public class Receive {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1111);
        while (true) {
            Runnable r = new MyRunnable(ss.accept());

            Thread t = new Thread(r);

            t.start();

        }
    }
}
