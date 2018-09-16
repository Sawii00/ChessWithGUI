package application.subsystem.IO;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class File {
	String name;
	String text;
	
	
	public File(String name, String text) {
		this.name = name;
		this.text = text;
		
		
	}
	
	public void writeToDisk(String path) {
		
		try {
			FileOutputStream fout = new FileOutputStream(path+name+".chs");
			DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(fout));
			dout.writeUTF(text);
			dout.flush();
			dout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("File Created");
		
	}
	
	

}
