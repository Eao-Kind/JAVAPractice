package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class project2 {
	public static void main(String[] args) throws FileNotFoundException {
		TextTool tl = new TextTool();
		ArrayList<String> wordList = tl.getAllWord(new File("English.txt")); // ��ȡ�����б�
		System.out.println("һ�����ֵĵ�������" + wordList.size());

		Map<String, Integer> mp = tl.wordStatistics(wordList); // ���ֵ�˳������
		System.out.println("������ͬ�ĵ�������" + mp.size());
		System.out.println("���ֵ�˳��");
		tl.myPrint(mp); // ����鿴

		tl.mySort(mp); // ���մ�Ƶ˳������
	}
}

class TextTool {

	public ArrayList<String> getAllWord(File source) throws FileNotFoundException {
		Scanner sc = new Scanner(source); // ��ȡ�ļ�
		ArrayList<String> ls = new ArrayList<String>(); // ��ȡ���е�����ɵ��б�
		while (sc.hasNextLine()) {
			String regex = "[a-zA-z]+"; // ������ʽ
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(sc.next());
			while (m.find()) {
				ls.add(m.group().toLowerCase()); // ��ȡ���� �� ת��ΪСд
			}
		}
		sc.close();
		return ls;
	}

	// ��Ƶͳ�� Ĭ�ϰ����ֵ�����
	public Map<String, Integer> wordStatistics(ArrayList<String> temp) {
		Map<String, Integer> mp = new TreeMap<String, Integer>(); // ��ֵ��
		for (String st : temp) {
			if (mp.get(st) != null) {
				mp.put(st, mp.get(st) + 1); // ��Ƶͳ��+1
			} else {
				mp.put(st, 1);
			}
		}
		return mp;
	}

	// Map ��Value����
	public void mySort(Map<String, Integer> temp) {
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(temp.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue()); // ��ֵ����
			}
		});
		System.out.println("����Ƶ˳��");
		for (Map.Entry<String, Integer> entry : list) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

	public void myPrint(Map mp) { // �Զ�����������鿴���
		Iterator iter = mp.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) iter.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
}