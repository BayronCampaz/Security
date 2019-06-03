package model;

import java.io.IOException;

public class Tests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeyGenerator generator = new KeyGenerator();
		try {
			
			generator.createKeys(1024, "Gitana gitana");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
