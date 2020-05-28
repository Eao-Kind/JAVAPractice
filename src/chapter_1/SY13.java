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
		CopyFileDemo.Solution("诗词.txt", "诗词反转.txt");
	}
}

class CopyFileDemo {
	public static void Solution(String fr, String fw) throws IOException {
		File fWrite = new File(fw); // 输出文件
		if (!fWrite.exists()) {
			fWrite.createNewFile(); // 不存在则创建文件
		}
		try {
			String str = null;
			StringBuffer strb = new StringBuffer();

			BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(fr), "utf-8")); // 缓冲读取
			Writer out = new FileWriter(fWrite);
			BufferedWriter bfw = new BufferedWriter(out); // 缓冲写入

			while ((str = bfr.readLine()) != null) {
				strb.append(str + "\n"); // 读取数据
				System.out.println(str);
			}
			strb.delete(strb.length() - 1, strb.length());
			bfw.write(strb.reverse().toString()); // 写入数据
			bfr.close();
			bfw.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}
