package project;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class project1LZ {
	public static void main(String[] args) {
		String allName = "郑晓宜,陈海金,莫雨林,薛灿杰,潘巧妍,蔡晓升,黄志翔,苏丽凤,吴泽强,李炎英,梁雷,陈嘉柔 ,许志远,尹傲,蔡汇康,"
		+ "郑智灿,杨宇平,林海强,林家彦,刘骏杰,黎志柱,罗润,黄廉智,郭雨宜,孔明,卢英倩,王嘉慧,列城托,罗睿致,古源林,陆海威,张香婷,"
		+ "王振宇,雷成明,罗正勰,石世鑫,常欣,孙伟男,李金昕";

		String[] list = allName.split(",");
		new win(list);
	}
}

class win extends JFrame {
	Listener ls = new Listener();

	public win(String[] list) {
		init(list);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	void init(String[] list) {
		setLayout(null);
		setTitle("18信计2班点名系统");
		setBounds(10, 10, 460, 360);
		JLabel t1 = new JLabel("点名人数");
		JTextField t2 = new JTextField(10);
		JButton b1 = new JButton("确定");
		JLabel t3 = new JLabel("被点中的名单如下：");
		JTextArea t4 = new JTextArea(20, 20);
		t4.setFont(new Font("宋体", Font.BOLD, 12));
		t1.setBounds(80, 10, 100, 25);
		t2.setBounds(140, 10, 100, 25);
		b1.setBounds(250, 10, 100, 25);
		t3.setBounds(135, 40, 200, 25);
		t4.setBounds(40, 60, 360, 200);
		add(t1);
		add(t2);
		add(b1);
		add(t3);
		add(t4);
		ls.set(t2, t4);
		ls.setl(list);
		b1.addActionListener(ls);
	}
}

class Listener implements ActionListener {
	JTextField t1;
	JTextArea t2;
	String[] list2;

	public void set(JTextField text, JTextArea area) {
		this.t1 = text;
		this.t2 = area;
	}

	public void setl(String[] list) {
		this.list2 = list;
	}

	public void actionPerformed(ActionEvent e) {
		for(String name : list2) System.out.println(name);
		int m = Integer.parseInt(t1.getText()); // 获取点名人数
		String str = e.getActionCommand();
		if (str.equals("确定")) {
			t2.setText("");
			int a[] = new int[m];
			a = randomCommon(1, 39, m); // m 个随机数
			for (int i = 0; i < m; i++) {
				if (i % 4 == 0) {
					t2.append("\n");
				}
				t2.append(a[i]+1 + "号" + list2[a[i]] + "、");
			}
		}
	}

	private static int[] randomCommon(int min, int max, int n) {
		if (n > (max - min + 1) || max < min) {
			System.out.println("超出范围");
		}

		HashSet<Integer> a = new HashSet<Integer>();
		while (a.size() < n) {
			int number = (int) (Math.random() * max + 1 - min);
			a.add(number);
		}
		int[] b = new int[n];
		int count = 0;
		for (int i : a) {
			b[count++] = i;
		}
		Arrays.sort(b); // 排序
		return b;
	}
}