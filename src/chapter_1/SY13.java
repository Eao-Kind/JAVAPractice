package chapter_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

public class SY13 {
	public static void main(String[] args) throws IOException {
		File fWrite = new File("诗词反转.txt"); // 输出文件
		String str = null;
		StringBuffer strb = new StringBuffer();
		try {
			BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream("诗词.txt"), "utf-8")); // 缓冲读取
			BufferedWriter bfw = new BufferedWriter(new FileWriter(fWrite)); // 缓冲写入
			while ((str = bfr.readLine()) != null)
				strb.append(str + "\n"); // 读取数据
			strb.delete(strb.length() - 1, strb.length());
			bfw.write(strb.reverse().toString()); // 写入数据
			bfr.close();
			bfw.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}
