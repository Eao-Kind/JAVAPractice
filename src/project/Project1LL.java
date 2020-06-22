package project;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

class randomrollcall // �������С���
{
	public static void main(String[] args) {
		new window();
	}
}

class readTheListOfStudents // ����ѧ������
{
	public ArrayList<String> readtxt() {
		ArrayList<String> result = new ArrayList<String>();
		File listname = new File("D:\\Code\\Java\\Study\\JavaSE\\Student.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(listname));// ��������������
			String str = null;
			while ((str = reader.readLine()) != null) {// ʹ��readLine������һ�ζ�һ��
				result.add(str);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}

class window extends JFrame // ����
{
	private static final long serialVersionUID = -5688475289018188717L;

	window() {
		JFrame windows = new JFrame();
		setLayout(null);
		windows.getContentPane().setBackground(Color.gray);// ���ô�����ɫ
		setBounds(800, 300, 460, 460);
		init();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void init() {
		setTitle("�������С���");
		JLabel label1 = new JLabel("����������");// ��ǩ
		label1.setBounds(100, 10, 100, 30);
		label1.setFont(new Font("����", Font.BOLD, 18));
		add(label1);

		JTextField text = new JTextField(10);// �����
		text.setBounds(200, 10, 100, 30);
		text.setFont(new Font("����", Font.BOLD, 18));
		add(text);

		Listen listen = new Listen();
		listen.setJTextField(text);// ������

		JButton getSelectNumber = new JButton("ȷ��");// ȷ�����ѡȡ������
		getSelectNumber.setBounds(320, 10, 80, 30);
		getSelectNumber.setFont(new Font("����", Font.BOLD, 18));
		add(getSelectNumber);
		getSelectNumber.addActionListener(listen);

		JLabel label2 = new JLabel("�����е��������£�");// ��ǩ
		label2.setBounds(140, 40, 200, 30);
		label2.setFont(new Font("����", Font.BOLD, 18));
		add(label2);

		JTextArea textArea = new JTextArea();// ����ı���
		listen.setJTextArea(textArea);// ������
		textArea.setBounds(75, 80, 300, 300);
		textArea.setFont(new Font("����", Font.BOLD, 18));
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
		int[] number = new int[count]; // ����ѧ������
		String str = e.getActionCommand();
		if (str.equals("ȷ��")) {
			try {
				textArea.setText("");
				HashSet<Integer> hs = new HashSet<Integer>();
				while (hs.size() < count) {
					hs.add(new Random().nextInt(39));// ����1��0-39���������
				}
				count = 0;
				for (int i : hs) {
					number[count++] = i;
				}
				readTheListOfStudents r = new readTheListOfStudents();
				ArrayList<String> re = r.readtxt();
				Arrays.sort(number); // ����
				for (int i : number) {
					textArea.append(i + 1 + "��" + re.get(i) + "\n");// �ı������
				}
			} catch (NumberFormatException event) {}
		}
	}
}
