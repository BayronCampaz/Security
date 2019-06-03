package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

import sun.misc.BASE64Encoder;

public class Checker {

	private Signature signature;
	
	public Checker() throws NoSuchAlgorithmException {		
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
	
	public boolean verify(String textPlain, String signatureString, PublicKey publicKey) throws Exception {
		
		signature.initVerify(publicKey);
	    signature.update(textPlain.getBytes("UTF-8"));
	    byte[] signatureBytes = Base64.getDecoder().decode(signatureString);

	    return signature.verify(signatureBytes);
		
	}
}
