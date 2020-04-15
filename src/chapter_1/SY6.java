package chapter_1;

public class SY6 {
	public static void main(String[] args) {
		Undergraduate ben = new Undergraduate(20); // ������
		ben.speak(); // ���Ǳ�����
		ben.supervisor();

		Graduate yan = new Graduate(24); // �о���
		yan.speak();
		yan.supervisor();

		PhDstudent bo = new PhDstudent(27); // ��ʿ��
		bo.speak();
		bo.supervisor();
	}
}

class UniversityStudent {
	int age;

	void speak() {
		String name = this.getClass().getName();
		System.out.println("����:" + name.split("\\.")[1]);
	}
}

class Undergraduate extends UniversityStudent {
	Undergraduate(int age) {
		this.age = age;
	}

	void supervisor() {
		System.out.println("��" + age + "��,û�е�ʦ");
	}
}

class Graduate extends UniversityStudent {
	Graduate(int age) {
		this.age = age;
	}

	void supervisor() {
		System.out.println("��" + age + "��,�е�ʦ����ʦ��good");
	}
}

class PhDstudent extends Graduate {
	PhDstudent(int age) {
		super(age);
	}

	void supervisor() {
		System.out.println("��" + age + "��,��ʦ�Ƕ�������");
	}
}
