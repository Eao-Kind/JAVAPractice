package project;

import java.util.Scanner;

public class project3 {
	public static void main(String[] args) {
		Person p1 = new Person("玩家1");
		Person p2 = new Person("玩家2");
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
		System.out.println("---------------------------游戏开始---------------------------");
		if (gameName == "myDiceGames") {
			System.out.println("各字母含义--> r 再掷一次 ; h 保留之前的得分\n ");
			new Games().myDiceGames(this, that);
		}
		System.out.println("---------------------------游戏结束---------------------------");
	}
}

class Games {
	public void myDiceGames(Person p1, Person p2) {
		int count1, count2 = 0;
		int i = 0;
		while (p1.count < 100 && p2.count < 100) { // 自定义掷的规则
			DiceGames dg = new DiceGames();
			System.out.print(p1.name);
			count1 = dg.rollMyOneDice();
			p1.count += count1;
			System.out.print(p2.name);
			count2 = dg.rollMyOneDice();
			p2.count += count2;
			System.out.printf("\n第%d轮游戏 \n %s获得%d分，总得分%d分 \n %s获得%d分，总得分%d分 \n\n", ++i, p1.name, count1, p1.count,
					p2.name, count2, p2.count);
		}
	}
}

class DiceGames {

	Scanner sc = new Scanner(System.in);

	public static int roll() {
		return (int) (Math.random() * 6) + 1; // 返回一个随机数1-6
	}

	public int rollMyOneDice() {
		int count = 0; // 得分总和
		int num = roll();
		if (num != 1) {
			System.out.print("掷色子得到点数：" + num + " 请输入 r/h : ");
			String r = sc.nextLine();
			while (!(r.equals("r") || r.equals("h"))) {
				System.out.print("输入错误请从新输入：");
				r = sc.nextLine();
			}
			if (r.equals("r")) { // 再抛一次
				count += roll();
				System.out.println("得到点数" + count);
			} else if (r.equals("h")) { // 不抛了
				count += num;
			}
		} else {
			System.out.println("掷色子得到点数：1，得0分,换玩家");
		}
		return count;
	}
}