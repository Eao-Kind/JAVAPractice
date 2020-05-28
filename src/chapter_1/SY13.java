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
		CopyFileDemo.Solution("ʫ��.txt", "ʫ�ʷ�ת.txt");
	}
}

class CopyFileDemo {
	public static void Solution(String fr, String fw) throws IOException {
		File fWrite = new File(fw); // ����ļ�
		if (!fWrite.exists()) {
			fWrite.createNewFile(); // �������򴴽��ļ�
		}
		try {
			String str = null;
			StringBuffer strb = new StringBuffer();

			BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(fr), "utf-8")); // �����ȡ
			Writer out = new FileWriter(fWrite);
			BufferedWriter bfw = new BufferedWriter(out); // ����д��

			while ((str = bfr.readLine()) != null) {
				strb.append(str + "\n"); // ��ȡ����
				System.out.println(str);
			}
			strb.delete(strb.length() - 1, strb.length());
			bfw.write(strb.reverse().toString()); // д������
			bfr.close();
			bfw.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}
