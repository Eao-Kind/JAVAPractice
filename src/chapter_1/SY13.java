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
		File fWrite = new File("ʫ�ʷ�ת.txt"); // ����ļ�
		String str = null;
		StringBuffer strb = new StringBuffer();
		try {
			BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream("ʫ��.txt"), "utf-8")); // �����ȡ
			BufferedWriter bfw = new BufferedWriter(new FileWriter(fWrite)); // ����д��
			while ((str = bfr.readLine()) != null)
				strb.append(str + "\n"); // ��ȡ����
			strb.delete(strb.length() - 1, strb.length());
			bfw.write(strb.reverse().toString()); // д������
			bfr.close();
			bfw.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}
