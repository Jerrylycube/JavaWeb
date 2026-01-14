package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 10001);
        System.out.println("连接成功");

        while (true) {
            System.out.println("欢迎来到Jerry的聊天室");
            System.out.println("1.登录");
            System.out.println("2.注册");

            Scanner sc = new Scanner(System.in);
            String option = sc.nextLine();
            switch (option) {
                case "1" -> login(socket);
                case "2" -> register(socket);
                default -> System.out.println("没有这个选项");
            }
        }


    }

    public static void register(Socket socket) throws IOException {
        System.out.println("您选择了注册功能");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        bw.write("register");
        bw.newLine();
        bw.flush();
    }

    public static void login(Socket socket) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


        Scanner sc = new Scanner(System.in);
        System.out.println("请输入账户");
        String inputName = sc.nextLine();
        System.out.println("请输入密码");
        String inputPassword = sc.nextLine();
        //拼接发送给服务端
        StringBuilder sb = new StringBuilder();
        //name=inputName&password=inputPassword
        sb.append("name=").append(inputName).append("&password=").append(inputPassword);

        bw.write("login");
        bw.newLine();
        bw.flush();

        bw.write(sb.toString());
        bw.newLine();
        bw.flush();

        //接收回显，看是否正常与错误
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String option = br.readLine();
        System.out.println(option);
        switch (option) {
            case "1" -> {
                System.out.println("成功登录！");
                //创建线程去接收打印聊天室的内容
                new Thread(new ClientMyRunnable(socket)).start();
                talk2All(bw);
            }
            case "2" -> System.out.println("密码错误");
            case "3" -> System.out.println("账户错误");
        }


    }

    //往服务器写出消息
    private static void talk2All(BufferedWriter bw) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要说的话");
        while (true) {
            String str = sc.nextLine();
            //把聊天内容写给服务器
            bw.write(str);
            bw.newLine();
            bw.flush();
        }
    }
}
