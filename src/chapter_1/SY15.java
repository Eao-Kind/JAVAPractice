package chapter_1;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SY15 {
	public static void main(String[] args) {
		String[] bookName = { "Java2SE", "JavaEE", "JavaWeb", "���ݽṹ", "C++�������", "Java2ME", "����ϵͳ" };
		Double[] bookPrice = { 29.0, 21.0, 22.0, 29.0, 34.0, 32.0, 29.0 };
		List<Book> bookList = new LinkedList<Book>();
		for (int i = 0; i < bookPrice.length; i++) {
			bookList.add(new Book(bookName[i], bookPrice[i]));
		}
		System.out.println("����ǰ�������е�����");
		for (Book b : bookList) {
			System.out.println(b.name + "---" + b.price);
		}
		Collections.sort(bookList);
		System.out.println("\n����������е�����");
		for (Book b : bookList) {
			System.out.println(b.name + "---" + b.price);
		}
		Book js = new Book("JavaScript", 29.0);
		int index = Collections.binarySearch(bookList, js, null);
		if (index >= 0) {
			System.out.println("\r\n" + js.name + " �������� " + bookList.get(index).name + " �۸���ͬ");
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

	public int compareTo(Object o) { // ����Book��ȵ��ҽ���price��
		Book that = (Book) o;
		return (int) (this.price - that.price);
	}
}