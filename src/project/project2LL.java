package project;

import java.util.regex.*;
import java.io.*;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.*;
import java.util.Comparator;

class project2LL // Ӣ�ĵ���ͳ��С����
{
	public static void main(String[] args) {
		String s = readEnglishTxt.readET();
		System.out.println("ԭ�ַ������Ϊ:\n" + s.toString());
		countEnglish.countWords(s);
	}
}

class readEnglishTxt // scanner�����Ӣ�ĵ����ı�
{
	public static String readET() {
		String str = "";
		try {
			Scanner readenglish = new Scanner(new File("English.txt"));
			while (readenglish.hasNext()) {
				str += readenglish.nextLine(); // ��ȡ�����ı�ֵ
			}
			readenglish.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return str;
	}
}

class countEnglish // ͳ��Ӣ�ĵ�������
{
	public static void countWords(String str) {
		int count = 0;
		Map<String, Integer> map = new TreeMap<String, Integer>();
		Pattern p = Pattern.compile("\\b[a-zA-Z-]+\\b");// ������ʽ
		Matcher m = p.matcher(str);
		while (m.find()) {
			String mstr = m.group();
			if (map.containsKey(mstr)) {
				map.put(mstr, map.get(mstr) + 1);
			} else {
				map.put(mstr, 1);
			}
		}
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		Iterator<Entry<String, Integer>> it = entrySet.iterator();

		while (it.hasNext()) {
			Entry<String, Integer> next = it.next();
			count += next.getValue();
		}

		System.out.println("---------�����ʾʽ������--------\n" + "------------�������-------------\n" + "ͳ�����");
		System.out.println("һ����" + count + "������");
		System.out.println("һ����" + map.size() + "����ͬ�ĵ���");

		System.out.println("���ֵ�˳�������ͬ�ĵ��ʣ�"); // �ֵ�˳������
		Iterator<Entry<String, Integer>> ito = map.entrySet().iterator();
		while (ito.hasNext()) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) ito.next();
			System.out.println("����Ϊ�� " + entry.getValue() + " ����Ϊ�� " + entry.getKey());
		}

		System.out.println("�����ʳ���Ƶ����С���������");// ����Ƶ������
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		for (Entry<String, Integer> e : list) {
			System.out.println("����Ϊ" + e.getValue() + " ����Ϊ��" + e.getKey());
		}
	}
}