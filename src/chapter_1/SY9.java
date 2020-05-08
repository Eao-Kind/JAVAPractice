package chapter_1;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SY9 {
	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		String str = sc.nextLine(); // ��ȡstring
//		sc.close();
		String str = "�������170.5cm, �������180.0cm, С�����165.5cm";
		MethodScanner methodOne = new MethodScanner(str);
		MethodStringTokenizer methodTwo = new MethodStringTokenizer(str);
		System.out.println("String �������������=" + methodOne.ComSum() + "cm");
		System.out.println("String ��������ƽ�����=" + methodOne.ComAver() + "cm");
		System.out.println("StringTokenizer �������������=" + methodTwo.ComSum() + "cm");
		System.out.println("StringTokenizer ��������ƽ�����=" + methodTwo.ComAver() + "cm");
	}
}

class MethodScanner {
	public String str;
	public double sum;
	public int count = 0;

	public MethodScanner(String str) {
		this.str = str;
	}

	public double ComSum() {
		Scanner sc = new Scanner(str);
		String re = "[^0-9.]+"; // ������ʽ
		sc.useDelimiter(re); // �Է�����Ϊ�ָ��������ַ������Ӷ���ȡ���
		while (sc.hasNext()) {
			sum += sc.nextDouble();
			count++; // ��¼����
		}
		return sum;
	}

	public double ComAver() {
		// ����ƽ�����
		return sum / count;
	}

}

class MethodStringTokenizer {
	public String str;
	public double sum;
	public int count;

	public MethodStringTokenizer(String str) {
		this.str = str;
	}

	public double ComSum() {
		String st = "";
		String regex = "[0-9.]+"; //����
		Pattern p = Pattern.compile(regex);  
		Matcher m = p.matcher(str);
		while(m.find()) {
			st += m.group() + ",";  //��ȡ����Զ���Ϊ�ָ���
		}
		StringTokenizer str = new StringTokenizer(st, ","); //�Զ���Ϊ�ָ�
		count = str.countTokens(); //����
		while(str.hasMoreTokens()) {
			sum += Double.parseDouble((str.nextToken())); //stringתdouble
		}
		
		return sum;
	}

	public double ComAver() {
		return sum / count;
	}
}
