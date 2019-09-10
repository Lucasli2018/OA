package cn.itcast.test;

import java.io.File;

public class MoveFileTest {

	public static void main(String[] args) {
		
		File f1 = new File("e:\\a.txt");
		File f2 = new File("d:\\a.txt");
		f1.renameTo(f2);
	}
}
