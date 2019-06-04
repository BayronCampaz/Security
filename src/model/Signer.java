package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.List;


public class Signer {
	
	private Signature signature;
	
	public Signer() throws NoSuchAlgorithmException {
		
		signature = Signature.getInstance("SHA1WithRSA");		
	}
	
	public byte[] loadFile(String path) throws Exception {
			
		return  Files.readAllBytes(new File(path).toPath());
	}
	
	public byte[] sign(byte[] content, PrivateKey privateKey) throws Exception {
		
		signature.initSign(privateKey);
		signature.update(content);
		byte[] signatureBytes = signature.sign();
		
		return signatureBytes;
	}
	
	public void saveSignFile(String path, byte[] content) throws Exception {
		
	    File fileSign = new File(path);
	    
	    fileSign.createNewFile();

		ObjectOutputStream writer = new ObjectOutputStream
				(new FileOutputStream(fileSign));
		writer.writeObject(content);
		writer.close();
		
	}
}
