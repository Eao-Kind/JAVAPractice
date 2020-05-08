package chapter_1;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SY9 {
	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		String str = sc.nextLine(); // 读取string
//		sc.close();
		String str = "张三身高170.5cm, 李四身高180.0cm, 小明身高165.5cm";
		MethodScanner methodOne = new MethodScanner(str);
		MethodStringTokenizer methodTwo = new MethodStringTokenizer(str);
		System.out.println("String 类计算出的总身高=" + methodOne.ComSum() + "cm");
		System.out.println("String 类计算出的平均身高=" + methodOne.ComAver() + "cm");
		System.out.println("StringTokenizer 类计算出的总身高=" + methodTwo.ComSum() + "cm");
		System.out.println("StringTokenizer 类计算出的平均身高=" + methodTwo.ComAver() + "cm");
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
		String re = "[^0-9.]+"; // 正则表达式
		sc.useDelimiter(re); // 以非数字为分隔，解析字符串，从而提取身高
		while (sc.hasNext()) {
			sum += sc.nextDouble();
			count++; // 记录人数
		}
		return sum;
	}

	public double ComAver() {
		// 计算平均身高
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
		String regex = "[0-9.]+"; //正则
		Pattern p = Pattern.compile(regex);  
		Matcher m = p.matcher(str);
		while(m.find()) {
			st += m.group() + ",";  //提取身高以逗号为分隔。
		}
		StringTokenizer str = new StringTokenizer(st, ","); //以逗号为分隔
		count = str.countTokens(); //数量
		while(str.hasMoreTokens()) {
			sum += Double.parseDouble((str.nextToken())); //string转double
		}
		
		return sum;
	}

	public double ComAver() {
		return sum / count;
	}
}
