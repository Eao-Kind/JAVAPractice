package project;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

class randomrollcall // 随机点名小软件
{
	public static void main(String[] args) {
		new window();
	}
}

class readTheListOfStudents // 读入学生名单
{
	public ArrayList<String> readtxt() {
		ArrayList<String> result = new ArrayList<String>();
		File listname = new File("D:\\Code\\Java\\Study\\JavaSE\\Student.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(listname));// 创建输入流对象
			String str = null;
			while ((str = reader.readLine()) != null) {// 使用readLine方法，一次读一行
				result.add(str);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}

class window extends JFrame // 窗口
{
	private static final long serialVersionUID = -5688475289018188717L;

	window() {
		JFrame windows = new JFrame();
		setLayout(null);
		windows.getContentPane().setBackground(Color.gray);// 设置窗口颜色
		setBounds(800, 300, 460, 460);
		init();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void init() {
		setTitle("随机点名小软件");
		JLabel label1 = new JLabel("点名人数：");// 标签
		label1.setBounds(100, 10, 100, 30);
		label1.setFont(new Font("楷体", Font.BOLD, 18));
		add(label1);

		JTextField text = new JTextField(10);// 输入框
		text.setBounds(200, 10, 100, 30);
		text.setFont(new Font("楷体", Font.BOLD, 18));
		add(text);

		Listen listen = new Listen();
		listen.setJTextField(text);// 监视器

		JButton getSelectNumber = new JButton("确认");// 确认随机选取的人数
		getSelectNumber.setBounds(320, 10, 80, 30);
		getSelectNumber.setFont(new Font("楷体", Font.BOLD, 18));
		add(getSelectNumber);
		getSelectNumber.addActionListener(listen);

		JLabel label2 = new JLabel("被点中的名单如下：");// 标签
		label2.setBounds(140, 40, 200, 30);
		label2.setFont(new Font("楷体", Font.BOLD, 18));
		add(label2);

		JTextArea textArea = new JTextArea();// 输出文本框
		listen.setJTextArea(textArea);// 监视器
		textArea.setBounds(75, 80, 300, 300);
		textArea.setFont(new Font("楷体", Font.BOLD, 18));
		add(textArea);
	}
}

class Listen implements ActionListener {
	JTextField text;
	JTextArea textArea;

	public void setJTextField(JTextField text) {
		this.text = text;
	}

	public void setJTextArea(JTextArea text) {
		this.textArea = text;
	}

	public void actionPerformed(ActionEvent e) {
		int count = Integer.valueOf(text.getText());
		int[] number = new int[count]; // 创建学号数组
		String str = e.getActionCommand();
		if (str.equals("确认")) {
			try {
				textArea.setText("");
				HashSet<Integer> hs = new HashSet<Integer>();
				while (hs.size() < count) {
					hs.add(new Random().nextInt(39));// 生成1个0-39的随机整数
				}
				count = 0;
				for (int i : hs) {
					number[count++] = i;
				}
				readTheListOfStudents r = new readTheListOfStudents();
				ArrayList<String> re = r.readtxt();
				Arrays.sort(number); // 升序
				for (int i : number) {
					textArea.append(i + 1 + "号" + re.get(i) + "\n");// 文本框输出
				}
			} catch (NumberFormatException event) {}
		}
	}
}
