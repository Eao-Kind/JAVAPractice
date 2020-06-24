package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.*;

public class project2 {
	public static void main(String[] args) throws FileNotFoundException {
		TextTool tl = new TextTool();
		ArrayList<String> wordList = tl.getAllWord(new File("test.txt"), "[A-Za-z]+"); // 获取单词列表 正则表达式
		System.out.println("\n一共出现的单词数：" + wordList.size());
		Map<String, Integer> mp = tl.wordStatistics(wordList); // 按字典顺序排序
		System.out.println("互不相同的单词数：" + mp.size() + "\n\n按字典顺序：");

		for (String key : mp.keySet()) 
			System.out.println("单词个数：" + mp.get(key) + " 单词为：" + key);

		List<Entry<String, Integer>> list = tl.mySort(mp); // 按照词频顺序排序
		System.out.println("\n按词频顺序：");
		for (Map.Entry<String, Integer> entry : list) 
			System.out.println("单词个数：" + entry.getValue() + " 单词为：" + entry.getKey());
	}
}

class TextTool {

	public ArrayList<String> getAllWord(File source, String regex) throws FileNotFoundException { // 根据传入的正则获取单词列表
		Scanner sc = new Scanner(source); // 读取文件
		ArrayList<String> ls = new ArrayList<String>(); // 获取所有单词组成的列表
		while (sc.hasNext()) {
			Pattern p = Pattern.compile(regex);
			String str = sc.nextLine();
			System.out.println(str);
			Matcher m = p.matcher(str);
			while (m.find())
				ls.add(m.group().toLowerCase()); // 提取单词 并 转换为小写
		}
		sc.close();
		return ls;
	}

	public Map<String, Integer> wordStatistics(ArrayList<String> temp) { // 词频统计 默认按照字典排序
		Map<String, Integer> mp = new TreeMap<String, Integer>(); // 键值对
		for (String st : temp) {
			if (mp.containsKey(st))
				mp.put(st, mp.get(st) + 1); // 词频统计+1
			else
				mp.put(st, 1);
		}
		return mp;
	}

	public List<Entry<String, Integer>> mySort(Map<String, Integer> temp) { // Map 按Value排序
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(temp.entrySet());
		list.sort(Map.Entry.comparingByValue());
		return list;
	}
}