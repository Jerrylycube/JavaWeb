package Server;

import java.io.*;
import java.net.Socket;
import java.util.Properties;
import java.util.Set;

public class ServerMyRunnable implements Runnable {
    Properties ppt;
    Socket socket;

    public ServerMyRunnable(Properties ppt, Socket socket) {
        this.ppt = ppt;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String option = br.readLine();
                switch (option) {
                    case "login" -> login(br);
                    case "register" -> System.out.println("用户选择了注册选项");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void login(BufferedReader br) throws IOException {
        String str = br.readLine();
        String[] input = str.split("&");
        String inputName = input[0].split("=")[1];
        String inputPassword = input[1].split("=")[1];
        System.out.println("用户输入的用户名为:" + inputName);
        System.out.println("用户输入的密码为:" + inputPassword);

        if (ppt.containsKey(inputName)) {
            String rightPassword = ppt.get(inputName) + "";
            if (rightPassword.equals(inputPassword)) {
                //账户密码都正确，开始群发,回显1
                chatToOne("1");
                //将这个建立连接的，加入到朋友链中
                Server.list.add(socket);
                //内容转发
                chatToAll(br, inputName);

            } else {
                chatToOne("2");
            }
        } else {
            chatToOne("3");
        }
    }

    public void chatToOne(String content) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write(content);
        bw.newLine();
        bw.flush();

    }

    public void chatToAll(BufferedReader br, String name) throws IOException {

        while (true) {
            String content = br.readLine();

            for (Socket socketEvery : Server.list) {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socketEvery.getOutputStream()));
                bw.write(name + "说 " + content);
                bw.newLine();
                bw.flush();
            }
        }
    }

}
