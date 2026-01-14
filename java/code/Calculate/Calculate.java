import java.util.Scanner;

public class Calculate {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入一个三位数");
		int num = sc.nextInt();
		int one = num % 10;
		int two = num / 10 % 10;
		int three = num / 100 % 10;
		System.out.println("您输入的三位数是" + num );
		System.out.println("百位是" + three + "十位是" + two + "个位是" + one);
		
		//短路&&和||，区别与&和|，&和|都会执行完全部
		int a = 10;
		int b = 10;
		boolean re = a++ < 5 && b++ >5;
		System.out.println(re);
		System.out.println(a);
		System.out.println(b);

				//短路&&和||，区别与&和|，&和|都会执行完全部
		int d = 10;
		int f = 10;
		boolean re2 = d++ < 5 & f++ >5;
		System.out.println(re2);
		System.out.println(d);
		System.out.println(f);

	}
}