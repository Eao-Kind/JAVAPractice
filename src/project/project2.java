package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.*;

public class project2 {
	public static void main(String[] args) throws FileNotFoundException {
		TextTool tl = new TextTool();
		ArrayList<String> wordList = tl.getAllWord(new File("test.txt"), "[A-Za-z]+"); // ��ȡ�����б� ������ʽ
		System.out.println("\nһ�����ֵĵ�������" + wordList.size());
		Map<String, Integer> mp = tl.wordStatistics(wordList); // ���ֵ�˳������
		System.out.println("������ͬ�ĵ�������" + mp.size() + "\n\n���ֵ�˳��");

		for (String key : mp.keySet()) 
			System.out.println("���ʸ�����" + mp.get(key) + " ����Ϊ��" + key);

		List<Entry<String, Integer>> list = tl.mySort(mp); // ���մ�Ƶ˳������
		System.out.println("\n����Ƶ˳��");
		for (Map.Entry<String, Integer> entry : list) 
			System.out.println("���ʸ�����" + entry.getValue() + " ����Ϊ��" + entry.getKey());
	}
}

class TextTool {

	public ArrayList<String> getAllWord(File source, String regex) throws FileNotFoundException { // ���ݴ���������ȡ�����б�
		Scanner sc = new Scanner(source); // ��ȡ�ļ�
		ArrayList<String> ls = new ArrayList<String>(); // ��ȡ���е�����ɵ��б�
		while (sc.hasNext()) {
			Pattern p = Pattern.compile(regex);
			String str = sc.nextLine();
			System.out.println(str);
			Matcher m = p.matcher(str);
			while (m.find())
				ls.add(m.group().toLowerCase()); // ��ȡ���� �� ת��ΪСд
		}
		sc.close();
		return ls;
	}

	public Map<String, Integer> wordStatistics(ArrayList<String> temp) { // ��Ƶͳ�� Ĭ�ϰ����ֵ�����
		Map<String, Integer> mp = new TreeMap<String, Integer>(); // ��ֵ��
		for (String st : temp) {
			if (mp.containsKey(st))
				mp.put(st, mp.get(st) + 1); // ��Ƶͳ��+1
			else
				mp.put(st, 1);
		}
		return mp;
	}

	public List<Entry<String, Integer>> mySort(Map<String, Integer> temp) { // Map ��Value����
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(temp.entrySet());
		list.sort(Map.Entry.comparingByValue());
		return list;
	}
}