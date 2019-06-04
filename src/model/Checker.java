package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;



public class Checker {

	private Signature signature;
	
	public Checker() throws NoSuchAlgorithmException {		
		signature = Signature.getInstance("SHA1WithRSA");		
	}
	
	public byte[] loadFile(String path) throws Exception {
		
		
		return Files.readAllBytes(new File(path).toPath());
	}
	
	public boolean verify(byte[] textPlain, byte[] signatureString, PublicKey publicKey) throws Exception {
		
		signature.initVerify(publicKey);
	    signature.update(textPlain);
	    //byte[] signatureBytes = signatureString.getBytes("UTF-8") ;

	    return signature.verify(signatureString);
		
	}
}
