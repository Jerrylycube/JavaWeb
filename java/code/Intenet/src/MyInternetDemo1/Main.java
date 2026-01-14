package MyInternetDemo1;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address =InetAddress.getByName("10.37.242.254");
        System.out.println(address);

        System.out.println(Arrays.toString(address.getAddress()));
        System.out.println(address.getHostAddress());
        System.out.println(address.getHostName());
    }
}
