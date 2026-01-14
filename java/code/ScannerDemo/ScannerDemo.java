import java.util.Scanner;

public class ScannerDemo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number1: ");
		int num1 = sc.nextShort();
		System.out.println("Enter a number2: ");
		int num2 = sc.nextInt();
		int sum = num1 + num2;
		System.out.println("sum = "+ sum);
		int a = 10;
		int d = a;
		int b = d++;
		int c = ++d;
		boolean ch = (a>c)?true:false;
		System.out.println("a="+ a +" b="+ b +" c="+ c + ch);
	}
}