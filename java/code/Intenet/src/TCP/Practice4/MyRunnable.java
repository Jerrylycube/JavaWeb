package TCP.Practice4;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class MyRunnable implements Runnable {

    private Socket accept;

    MyRunnable(Socket accept) {
        this.accept = accept;
    }

    @Override
    public void run() {
        try {
            String name = UUID.randomUUID().toString().replace("-", "");
            BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("serever\\" + name + ".png"));
            BufferedInputStream br = new BufferedInputStream(accept.getInputStream());
            int b;
            byte[] n = new byte[1024];
            while ((b = br.read(n)) != -1) {
                bw.write(n, 0, b);
                System.out.println("nh");
            }

            //回写
            OutputStreamWriter bsw = new OutputStreamWriter(accept.getOutputStream());
            bsw.write("好了");

            bw.flush();
            bw.close();
            bsw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (accept != null) {
                try {
                    accept.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
