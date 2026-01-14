package Server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

public class Server {
    static ArrayList<Socket> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10001);
        Properties ppt = new Properties();
        BufferedReader br = new BufferedReader(new FileReader("servicedir\\userinfo.txt"));
        ppt.load(br);
        br.close();

        while (true) {
            Socket socket = ss.accept();
            System.out.println("有人来连接");
            new Thread(new ServerMyRunnable(ppt, socket)).start();
        }

    }
}
