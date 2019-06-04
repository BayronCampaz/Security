package model;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Controller {

	
	private Generator generator;
	private Checker checker;
	private Signer signer;
	
	public Controller () {
		try {
			
			generator = new Generator();
			checker = new Checker();
			signer = new Signer();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createKeys(String alias, String password) throws Exception {	
		generator.createKeys(alias, 1024, password);	
	}
	
	public void signFile (String pathFile, String pathFileSigned, String password) throws Exception {
		PrivateKey privateKey = generator.getPrivateKey(password);
		byte[] content = signer.loadFile(pathFile);
		byte[] contentSigned = signer.sign(content, privateKey);
		signer.saveSignFile(pathFileSigned, contentSigned);	
	}
	
	public boolean verifyFile (String pathFile, String pathFileSigned, String password) throws Exception {
		PublicKey publicKey = generator.getPublicKey(password);
		byte[] textPlain = checker.loadFile(pathFile);
		System.out.println(new String(textPlain));
		System.out.println(new String());
		byte[] signatureString = checker.loadFile(pathFileSigned);
		return checker.verify(textPlain, signatureString, publicKey);
		
	}
}
