package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		ArrayList<String> wordList = tl.getAllWord(new File("English.txt"), "[a-zA-z]+"); // 获取单词列表 正则表达式
		System.out.println("一共出现的单词数：" + wordList.size());
		Map<String, Integer> mp = tl.wordStatistics(wordList); // 按字典顺序排序
		System.out.println("互不相同的单词数：" + mp.size());
		System.out.println("按字典顺序：");
		
		for(String key : mp.keySet()) {
			System.out.println("单词个数：" + mp.get(key) + " 单词为：" + key);
		}

		List<Entry<String, Integer>> list = tl.mySort(mp); // 按照词频顺序排序
		System.out.println("按词频顺序：");
		for (Map.Entry<String, Integer> entry : list) {
			System.out.println("单词个数：" + entry.getValue() + " 单词为：" + entry.getKey());
		}
	}
}

class TextTool {

	public ArrayList<String> getAllWord(File source, String regex) throws FileNotFoundException { // 根据传入的正则获取单词列表
		Scanner sc = new Scanner(source); // 读取文件
		ArrayList<String> ls = new ArrayList<String>(); // 获取所有单词组成的列表
		while (sc.hasNextLine()) {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(sc.next());
			while (m.find())
				ls.add(m.group().toLowerCase()); // 提取单词 并 转换为小写
		}
		sc.close();
		return ls;
	}

	public Map<String, Integer> wordStatistics(ArrayList<String> temp) { // 词频统计 默认按照字典排序
		Map<String, Integer> mp = new TreeMap<String, Integer>(); // 键值对
		for (String st : temp) {
			if (mp.get(st) != null)
				mp.put(st, mp.get(st) + 1); // 词频统计+1
			else
				mp.put(st, 1);
		}
		return mp;
	}

	public List<Entry<String, Integer>> mySort(Map<String, Integer> temp) { // Map 按Value排序
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(temp.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue()); // 按值排序
			}
		});
		return list;
	}
}