package project;

import java.util.Scanner;

public class project3 {
	public static void main(String[] args) {
		Person p1 = new Person("���1");
		Person p2 = new Person("���2");
		p1.playGames("myDiceGames", p2);
	}
}

class Person {
	String name;
	int count = 0;

	public Person(String name) {
		this.name = name;
	}

	public void playGames(String gameName, Person that) {
		System.out.println("---------------------------��Ϸ��ʼ---------------------------");
		if (gameName == "myDiceGames") {
			System.out.println("����ĸ����--> r ����һ�� ; h ����֮ǰ�ĵ÷�\n ");
			new Games().myDiceGames(this, that);
		}
		System.out.println("---------------------------��Ϸ����---------------------------");
	}
}

class Games {
	public void myDiceGames(Person p1, Person p2) {
		int count1, count2 = 0;
		int i = 0;
		while (p1.count < 100 && p2.count < 100) { // �Զ������Ĺ���
			DiceGames dg = new DiceGames();
			System.out.print(p1.name);
			count1 = dg.rollMyOneDice();
			p1.count += count1;
			System.out.print(p2.name);
			count2 = dg.rollMyOneDice();
			p2.count += count2;
			System.out.printf("\n��%d����Ϸ \n %s���%d�֣��ܵ÷�%d�� \n %s���%d�֣��ܵ÷�%d�� \n\n", ++i, p1.name, count1, p1.count,
					p2.name, count2, p2.count);
		}
	}
}

class DiceGames {

	Scanner sc = new Scanner(System.in);

	public static int roll() {
		return (int) (Math.random() * 6) + 1; // ����һ�������1-6
	}

	public int rollMyOneDice() {
		int count = 0; // �÷��ܺ�
		int num = roll();
		if (num != 1) {
			System.out.print("��ɫ�ӵõ�������" + num + " ������ r/h : ");
			String r = sc.nextLine();
			while (!(r.equals("r") || r.equals("h"))) {
				System.out.print("���������������룺");
				r = sc.nextLine();
			}
			if (r.equals("r")) { // ����һ��
				count += roll();
				System.out.println("�õ�����" + count);
			} else if (r.equals("h")) { // ������
				count += num;
			}
		} else {
			System.out.println("��ɫ�ӵõ�������1����0��,�����");
		}
		return count;
	}
}