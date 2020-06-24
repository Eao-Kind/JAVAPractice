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

class project2LL // 英文单词统计小程序
{
	public static void main(String[] args) {
		String s = readEnglishTxt.readET();
		System.out.println("原字符串输出为:\n" + s.toString());
		countEnglish.countWords(s);
	}
}

class readEnglishTxt // scanner类读入英文单词文本
{
	public static String readET() {
		String str = "";
		try {
			Scanner readenglish = new Scanner(new File("English.txt"));
			while (readenglish.hasNext()) {
				str += readenglish.nextLine(); // 获取本地文本值
			}
			readenglish.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return str;
	}
}

class countEnglish // 统计英文单词数量
{
	public static void countWords(String str) {
		int count = 0;
		Map<String, Integer> map = new TreeMap<String, Integer>();
		Pattern p = Pattern.compile("\\b[a-zA-Z-]+\\b");// 正则表达式
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

		System.out.println("---------正则表示式运算中--------\n" + "------------运算完成-------------\n" + "统计完成");
		System.out.println("一共有" + count + "个单词");
		System.out.println("一共有" + map.size() + "个不同的单词");

		System.out.println("按字典顺序输出不同的单词："); // 字典顺序排序
		Iterator<Entry<String, Integer>> ito = map.entrySet().iterator();
		while (ito.hasNext()) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) ito.next();
			System.out.println("个数为： " + entry.getValue() + " 单词为： " + entry.getKey());
		}

		System.out.println("按单词出现频率由小到大输出：");// 单词频率排序
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		for (Entry<String, Integer> e : list) {
			System.out.println("个数为" + e.getValue() + " 单词为：" + e.getKey());
		}
	}
}