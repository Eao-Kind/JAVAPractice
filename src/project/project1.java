package project;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class project1 {
	public static void main(String[] args) {
		WindowDemo win = new WindowDemo();
		PoliceListen police = new PoliceListen(); // ������
		win.setMyCommdangListener(police); // ���ü�����
		win.setBounds(300, 300, 400, 250); // ���ڴ�С
	}
}

class WindowDemo extends JFrame {
	JButton button1; // ȷ����ť
	JTextField randomText; // ��������
	JTextArea outputText; // ��ʾ���㵽��ѧ����Ϣ

	public WindowDemo() {
		init(); // ��ʼ��
		setVisible(true); // ���ӻ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �ر�
	}

	void init() {
		setLayout(null);
		setTitle("18�żƶ����������");
		JLabel lblNewLabel = new JLabel("����������");
		lblNewLabel.setBounds(50, 20, 70, 20);
		add(lblNewLabel);

		randomText = new JTextField(10); // ȷ��������������
		randomText.setBounds(110, 15, 120, 30);
		add(randomText);

		button1 = new JButton("ȷ��");
		button1.setBounds(270, 15, 70, 30);
		add(button1);

		JLabel lblNewLabel2 = new JLabel("�����е��������£�");
		lblNewLabel2.setBounds(100, 52, 120, 20);
		add(lblNewLabel2);

		outputText = new JTextArea(); // �����ȡ����ѧ����Ϣ
		outputText.setBounds(50, 80, 290, 100);
		add(outputText);
	}

	void setMyCommdangListener(MyCommandListener listener) {
		listener.setRandomJTextField(randomText); // �¼��������¼�������ϵ
		listener.setInputJTextArea(outputText); // �¼��������¼�������ϵ

		button1.addActionListener(listener); // ע�����
		randomText.addActionListener(listener); // ע�����
	}
}

interface MyCommandListener extends ActionListener {
	public void setRandomJTextField(JTextField text);

	public HashSet<Integer> getRandomNumber();

	public void setInputJTextArea(JTextArea text);
}

class PoliceListen implements MyCommandListener { // �¼�����
	JTextField randomText;
	JTextArea outputText;

	public void setRandomJTextField(JTextField text) {
		this.randomText = text;
	}

	public void setInputJTextArea(JTextArea text) {
		this.outputText = text;
	}

	public HashSet<Integer> getRandomNumber() { // ��ȡ���ѧ�ż���
		int num = Integer.valueOf(randomText.getText()); // ��ȡ��������������������
		HashSet<Integer> numSet = new HashSet<Integer>(); // ����ѧ�ŵĹ�ϣ����
		while (numSet.size() < num) { // ��ȡnum�����ظ����������
			int num1 = (int) (Math.random() * 39 + 1); // 1-39ѧ��
			numSet.add(num1);
		}
		return numSet;
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getSource().toString();

		if (str.contains("ȷ��")) {
			HashSet<Integer> nameSet = getRandomNumber(); // ��ȡ
			String nameDetail = showDetailName(nameSet);
			outputText.setText(nameDetail); // ��ʾ��ϸ��Ϣ
		}
	}

	private static String showDetailName(HashSet nameSet) {
		Mysql ms = new Mysql();
		String rs = ms.Solution(nameSet); // ����ѧ�Ż������
		return (rs); // ������ϸ��Ϣ
	}
}

class Mysql {
	String userName = "root";
	String password = "sca2269276";
	String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://192.168.1.204:336/test";

	public static Connection connection;

	{
		try { // ע������
			Class.forName(JDBC_DRIVER);
			System.out.println("�������سɹ�");
		} catch (ClassNotFoundException e) {
			System.out.println("��������ʧ��");
			e.printStackTrace();
		}
	}

	public Mysql() {
		try {
			connection = DriverManager.getConnection(url, userName, password); // ��ȡ����
			System.out.println("���ݿ����ӳɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("����ʧ�ܣ�");
		}
	}

	public String Solution(HashSet<Integer> hm) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.createStatement(); // ��ȡ���ݿ��������
			String nameDetail = "";
			int count = 0;
			for (int i : hm) {
				String sql = "select * from Sheet1 where Number = " + i; // ����ѧ�Ų�ѯ����
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					// System.out.println(rs.getString(2) + "��" + rs.getString(1));
					nameDetail += rs.getString(2) + "��" + rs.getString(1) + "��";
					count++;
				}
				if (count % 4 == 0)
					nameDetail += "\n";
			}
			return nameDetail; // ������Ϣ�ַ���
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return "";
	}
}