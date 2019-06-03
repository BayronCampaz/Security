package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;

import sun.misc.BASE64Encoder;

public class Signer {
	
	private Signature signature;
	
	public Signer() throws NoSuchAlgorithmException {
		
		signature = Signature.getInstance("SHA1WithRSA");		
	}
	
	public String loadFile(String path) throws Exception {
		String content = "";

		BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
		String line = "";
		while( ( line = reader.readLine()) != null) {
		        content += "/n" + line;
		        
		    }
		reader.close();
		
		return content;
	}
	
	public String sign(String content, PrivateKey privateKey) throws Exception {
		
		signature.initSign(privateKey);
		signature.update(content.getBytes("UTF-8"));
		byte[] signatureBytes = signature.sign();
		String signatureString = new BASE64Encoder().encode(signatureBytes);
		
		return signatureString;
	}
	
	public void saveSignFile(String path, String content) throws Exception {
		
	    File fileSign = new File(path);
	    
	    fileSign.createNewFile();

		ObjectOutputStream writer = new ObjectOutputStream
				(new FileOutputStream(fileSign));
		writer.writeObject(content);
		writer.close();
		
	}
}
