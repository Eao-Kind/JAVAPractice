package chapter_1;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SY15 {
	public static void main(String[] args) {
		String[] bookName = { "Java2SE", "JavaEE", "JavaWeb", "数据结构", "C++程序设计", "Java2ME", "操作系统" };
		Double[] bookPrice = { 29.0, 21.0, 22.0, 29.0, 34.0, 32.0, 29.0 };
		List<Book> bookList = new LinkedList<Book>();
		for (int i = 0; i < bookPrice.length; i++) {
			bookList.add(new Book(bookName[i], bookPrice[i]));
		}
		System.out.println("排序前：链表中的数据");
		for (Book b : bookList) {
			System.out.println(b.name + "---" + b.price);
		}
		Collections.sort(bookList);
		System.out.println("\n排序后：链表中的数据");
		for (Book b : bookList) {
			System.out.println(b.name + "---" + b.price);
		}
		Book js = new Book("JavaScript", 29.0);
		int index = Collections.binarySearch(bookList, js, null);
		if (index >= 0) {
			System.out.println("\r\n" + js.name + " 和链表中 " + bookList.get(index).name + " 价格相同");
		}
	}
}

class Book implements Comparable {
	String name;
	double price;

	Book(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public int compareTo(Object o) { // 两个Book相等当且仅当price等
		Book that = (Book) o;
		return (int) (this.price - that.price);
	}
}