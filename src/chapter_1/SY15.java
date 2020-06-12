package chapter_1;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SY15 {
	public static void main(String[] args) {
		String[] bookName = { "Java2SE", "JavaEE", "JavaWeb", "���ݽṹ", "C++�������", "Java2ME", "����ϵͳ" };
		Double[] bookPrice = { 29.0, 21.0, 22.0, 29.0, 34.0, 32.0, 29.0 };
		List<Book> bookList = new LinkedList<Book>();
		for (int i = 0; i < bookPrice.length; i++) 
			bookList.add(new Book(bookName[i], bookPrice[i]));
		System.out.println("����ǰ�������е�����");
		for (Book b : bookList) 
			System.out.println(b.name + "---" + b.price);
		Collections.sort(bookList);
		System.out.println("\n����������е�����");
		for (Book b : bookList) 
			System.out.println(b.name + "---" + b.price);
		System.out.println();
		Book js = new Book("JavaScript", 29.0);
		int index = 0;
		while ((index = Collections.binarySearch(bookList, js, null)) >= 0) {
			System.out.println(js.name + " �������� " + bookList.get(index).name + " �۸���ͬ");
			bookList.remove(index);
		}
	}
}

class Book implements Comparable<Book> {
	String name;
	double price;

	Book(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public int compareTo(Book that) { // ����Book��ȵ��ҽ���price��
		return (int) (this.price - that.price);
	}
}