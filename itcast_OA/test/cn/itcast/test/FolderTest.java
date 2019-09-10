package cn.itcast.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FolderTest {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd");
		Date date = new Date();
		String str = sdf.format(date);
		System.out.println(str);
		
		File file = new File("d:\\" + str);// D:\2013\12\23
		file.mkdirs();
	}
}
