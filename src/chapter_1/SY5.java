package chapter_1;

public class SY5 {
	public static void main(String[] args) {
//		System.out.println("方法1");
//		Cylinder cylinder2 = new Cylinder();
//		cylinder2.setRH(2, 3);
//		cylinder2.GetArea();
//		cylinder2.GetVolume();	
		System.out.println("方法2");
		Cylinder cylinder = new Cylinder(-2, 4);
		cylinder.GetArea();
		cylinder.GetVolume();
	}
}

class NoValueException extends RuntimeException {
	NoValueException(String message) {
		super(message);
	}
}

class Cylinder {
	public static final double PI = 3.14;
	public int r, h;

	Cylinder(int r, int h) { // 构造函数
		if (r <= 00 || h <= 0)
			throw new NoValueException("出现非法值");
		this.r = r;
		this.h = h;
	}

	Cylinder() {
	}

	void setRH(int r, int h) {
		this.r = r;
		this.h = h;
	}

	public void GetArea() {
		System.out.println(PI * r * r);
	}

	public void GetVolume() {
		System.out.println(PI * r * r * h);
	}
}