package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.EncryptedPrivateKeyInfo;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class Generator {
	
	public static final String DIR = "C:/encryption";
	public static final String PRIVATE_KEY_FILE = "C:/encryption/private.key";
	public static final String PUBLIC_KEY_FILE = "C:/encrytion/public.key";
	
	private KeyPairGenerator keyPairGenerator;
	private KeyStore keyStore;
	private HashMap<String, HashMap<PrivateKey , PublicKey>>  ();
	
	public Generator () throws Exception {
		
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			File dir = new File(DIR);
			dir.mkdirs();
			
	}
	
	
	public void createKeys(String alias, int sizeKey, String password ) throws Exception {
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
		
	}
	
	public String getPublicKey(String ) {
		
	}
	

}
