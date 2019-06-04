package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import javafx.util.Pair;

public class Generator {
	
	public static final String DIR = "data";
	public static final String PRIVATE_KEY_FILE = "data/private";
	public static final String PUBLIC_KEY_FILE = "data/public";
	
	private KeyPairGenerator keyPairGenerator;
	//private KeyStore keyStore;
	private HashMap<String, Pair<PrivateKey, PublicKey>> keys;
	
	public Generator () throws Exception {
		
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keys = new HashMap<String, Pair<PrivateKey, PublicKey>>();
			File dir = new File(DIR);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
	}
	
	
	public void createKeys(String alias, int sizeKey, String password ) throws Exception {
		keyPairGenerator.initialize(sizeKey);
		KeyPair keys = keyPairGenerator.generateKeyPair();
		
		Pair<PrivateKey, PublicKey> pair = new Pair<PrivateKey, PublicKey> (keys.getPrivate() , keys.getPublic());
		this.keys.put(password, pair );
		
	    File privateKeyFile = new File(PRIVATE_KEY_FILE);
	    File publicKeyFile = new File(PUBLIC_KEY_FILE);
	    
	    privateKeyFile.createNewFile();
	    publicKeyFile.createNewFile();
	    

		ObjectOutputStream publicKeyOS = new ObjectOutputStream
				(new FileOutputStream(publicKeyFile));
		publicKeyOS.writeObject(keys.getPublic());
		publicKeyOS.close();

		ObjectOutputStream privateKeyOS = new ObjectOutputStream
				(new FileOutputStream(privateKeyFile));
		privateKeyOS.writeObject(keys.getPrivate());
		privateKeyOS.close();	
		
	}
	
	public PrivateKey getPrivateKey(String password) {
		
		return keys.get(password).getKey();
	}
	
	public PublicKey getPublicKey(String password) {
		
		byte[] keyBytes;
		try {
			keyBytes = Files.readAllBytes(new File(PUBLIC_KEY_FILE).toPath());
			X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			return kf.generatePublic(spec);
			
		} catch (Exception e) {
		
			e.printStackTrace();
			return null;
		}
		
	}
	

}
