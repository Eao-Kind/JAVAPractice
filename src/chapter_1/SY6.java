package chapter_1;

public class SY6 {
	public static void main(String[] args) {
		Undergraduate ben = new Undergraduate(20); // 本科生
		ben.speak(); // 我是本科生
		ben.supervisor();

		Graduate yan = new Graduate(24); // 研究生
		yan.speak();
		yan.supervisor();

		PhDstudent bo = new PhDstudent(27); // 博士生
		bo.speak();
		bo.supervisor();
	}
}

class UniversityStudent {
	int age;

	void speak() {
		String name = this.getClass().getName();
		System.out.println("我是:" + name.split("\\.")[1]);
	}
}

class Undergraduate extends UniversityStudent {
	Undergraduate(int age) {
		this.age = age;
	}

	void supervisor() {
		System.out.println("我" + age + "岁,没有导师");
	}
}

class Graduate extends UniversityStudent {
	Graduate(int age) {
		this.age = age;
	}

	void supervisor() {
		System.out.println("我" + age + "岁,有导师，导师很good");
	}
}

class PhDstudent extends Graduate {
	PhDstudent(int age) {
		super(age);
	}

	void supervisor() {
		System.out.println("我" + age + "岁,导师是二级教授");
	}
}
