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
		ArrayList<String> wordList = tl.getAllWord(new File("English.txt")); // 获取单词列表
		System.out.println("一共出现的单词数：" + wordList.size());

		Map<String, Integer> mp = tl.wordStatistics(wordList); // 按字典顺序排序
		System.out.println("互不相同的单词数：" + mp.size());
		System.out.println("按字典顺序：");
		tl.myPrint(mp); // 输出查看

		tl.mySort(mp); // 按照词频顺序排序
	}
}

class TextTool {

	public ArrayList<String> getAllWord(File source) throws FileNotFoundException {
		Scanner sc = new Scanner(source); // 读取文件
		ArrayList<String> ls = new ArrayList<String>(); // 获取所有单词组成的列表
		while (sc.hasNextLine()) {
			String regex = "[a-zA-z]+"; // 正则表达式
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(sc.next());
			while (m.find()) {
				ls.add(m.group().toLowerCase()); // 提取单词 并 转换为小写
			}
		}
		sc.close();
		return ls;
	}

	// 词频统计 默认按照字典排序
	public Map<String, Integer> wordStatistics(ArrayList<String> temp) {
		Map<String, Integer> mp = new TreeMap<String, Integer>(); // 键值对
		for (String st : temp) {
			if (mp.get(st) != null) {
				mp.put(st, mp.get(st) + 1); // 词频统计+1
			} else {
				mp.put(st, 1);
			}
		}
		return mp;
	}

	// Map 按Value排序
	public void mySort(Map<String, Integer> temp) {
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(temp.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue()); // 按值排序
			}
		});
		System.out.println("按词频顺序：");
		for (Map.Entry<String, Integer> entry : list) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

	public void myPrint(Map mp) { // 自定义输出——查看结果
		Iterator iter = mp.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) iter.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
}