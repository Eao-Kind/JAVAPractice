package algorithms;

import java.util.ArrayList;

public class PowerSet {
	public static void main(String[] args) {
		String[] a = { "1", "2","3", "4" };
		ArrayList<String> pn = getSet(a); //
		System.out.println("Set size is = " + pn.size());
		System.out.println(pn.toString());
	}

	public static ArrayList<String> getSet(String[] a) {
		ArrayList<String> pn = new ArrayList<String>(); // creat a list to save set
		pn.add("");
		for (int i = 0; i < a.length; i++) {
			pn.addAll(IPN(pn, a[i])); // 递推式：p(n) = p(n-1) + IPN p(0) = {"","1"}; p(1) = p{"","1","2","1,2"}...
			// System.out.println("pn.addAll = " + pn.toString());
			// System.out.println("--------------------");
		}
		return pn;
	}

	public static ArrayList<String> IPN(ArrayList<String> pn, String i) {
		ArrayList<String> tmp = new ArrayList<String>();
		for (int k = 0; k < pn.size(); k++) {
			// System.out.println("pn.get[k] = " + pn.get(k));
			tmp.add(pn.get(k) + i); // 临时list存储修改该位置元素
			// System.out.printf("tmp.get(%d) = %s\n", k, tmp.get(k));
		}
		return tmp;
	}
}