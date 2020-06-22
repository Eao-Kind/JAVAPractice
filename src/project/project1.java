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
		PoliceListen police = new PoliceListen(); // 监听器
		win.setMyCommdangListener(police); // 设置监听器
		win.setBounds(300, 300, 400, 250); // 窗口大小
	}
}

class WindowDemo extends JFrame {
	JButton button1; // 确定按钮
	JTextField randomText; // 点名人数
	JTextArea outputText; // 显示被点到的学生信息

	public WindowDemo() {
		init(); // 初始化
		setVisible(true); // 可视化
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭
	}

	void init() {
		setLayout(null);
		setTitle("18信计二班随机点名");
		JLabel lblNewLabel = new JLabel("点名人数：");
		lblNewLabel.setBounds(50, 20, 70, 20);
		add(lblNewLabel);

		randomText = new JTextField(10); // 确定点名人数数量
		randomText.setBounds(110, 15, 120, 30);
		add(randomText);

		button1 = new JButton("确定");
		button1.setBounds(270, 15, 70, 30);
		add(button1);

		JLabel lblNewLabel2 = new JLabel("被点中的名单如下：");
		lblNewLabel2.setBounds(100, 52, 120, 20);
		add(lblNewLabel2);

		outputText = new JTextArea(); // 输出抽取到的学生信息
		outputText.setBounds(50, 80, 290, 100);
		add(outputText);
	}

	void setMyCommdangListener(MyCommandListener listener) {
		listener.setRandomJTextField(randomText); // 事件对象与事件处理联系
		listener.setInputJTextArea(outputText); // 事件对象与事件处理联系

		button1.addActionListener(listener); // 注册监听
		randomText.addActionListener(listener); // 注册监听
	}
}

interface MyCommandListener extends ActionListener {
	public void setRandomJTextField(JTextField text);

	public HashSet<Integer> getRandomNumber();

	public void setInputJTextArea(JTextArea text);
}

class PoliceListen implements MyCommandListener { // 事件处理
	JTextField randomText;
	JTextArea outputText;

	public void setRandomJTextField(JTextField text) {
		this.randomText = text;
	}

	public void setInputJTextArea(JTextArea text) {
		this.outputText = text;
	}

	public HashSet<Integer> getRandomNumber() { // 获取随机学号集合
		int num = Integer.valueOf(randomText.getText()); // 获取输入框中输入的人数数量
		HashSet<Integer> numSet = new HashSet<Integer>(); // 保存学号的哈希集合
		while (numSet.size() < num) { // 获取num个不重复的随机数字
			int num1 = (int) (Math.random() * 39 + 1); // 1-39学号
			numSet.add(num1);
		}
		return numSet;
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getSource().toString();

		if (str.contains("确定")) {
			HashSet<Integer> nameSet = getRandomNumber(); // 获取
			String nameDetail = showDetailName(nameSet);
			outputText.setText(nameDetail); // 显示详细信息
		}
	}

	private static String showDetailName(HashSet nameSet) {
		Mysql ms = new Mysql();
		String rs = ms.Solution(nameSet); // 根据学号获得姓名
		return (rs); // 返回详细信息
	}
}

class Mysql {
	String userName = "root";
	String password = "sca2269276";
	String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://192.168.1.204:336/test";

	public static Connection connection;

	{
		try { // 注册驱动
			Class.forName(JDBC_DRIVER);
			System.out.println("驱动加载成功");
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载失败");
			e.printStackTrace();
		}
	}

	public Mysql() {
		try {
			connection = DriverManager.getConnection(url, userName, password); // 获取连接
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("连接失败！");
		}
	}

	public String Solution(HashSet<Integer> hm) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.createStatement(); // 获取数据库操作对象
			String nameDetail = "";
			int count = 0;
			for (int i : hm) {
				String sql = "select * from Sheet1 where Number = " + i; // 根据学号查询姓名
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					// System.out.println(rs.getString(2) + "号" + rs.getString(1));
					nameDetail += rs.getString(2) + "号" + rs.getString(1) + "、";
					count++;
				}
				if (count % 4 == 0)
					nameDetail += "\n";
			}
			return nameDetail; // 返回信息字符串
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