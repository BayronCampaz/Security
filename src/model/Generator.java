package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.EncryptedPrivateKeyInfo;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Generator {
	
	public static final String DIR = "C:/keys";
	public static final String PRIVATE_KEY_FILE = "C:/keys/private.key";
	public static final String PUBLIC_KEY_FILE = "C:/keys/public.key";
	
	private KeyPairGenerator keyPairGenerator;
	private KeyStore keyStore;
	
	public Generator () {
		try {	
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			File dir = new File(DIR);
			dir.mkdirs();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
	}
	
	
	public void createKeys(int sizeKey, String password ) throws IOException {
		
		keyPairGenerator.initialize(sizeKey);
		KeyPair keys = keyPairGenerator.generateKeyPair();
		
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
		
		keyStore.
		
	}
	
	public PrivateKey getPrivateKey(byte[] encrypted, String password) throws Exception{

        EncryptedPrivateKeyInfo encryptInfo = new EncryptedPrivateKeyInfo(encrypted);      
        Cipher cipher = Cipher.getInstance(encryptInfo.getAlgName());
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory secFac = SecretKeyFactory.getInstance(encryptInfo.getAlgName());
        Key pbeKey = secFac.generateSecret(pbeKeySpec);
        AlgorithmParameters algParams = encryptInfo.getAlgParameters();
        cipher.init(Cipher.DECRYPT_MODE, pbeKey, algParams);
        KeySpec keySpec = encryptInfo.getKeySpec(cipher);
        KeyFactory kf = KeyFactory.getInstance("RSA"); // Dudas en esta linea
        return kf.generatePrivate(keySpec);
}
}
