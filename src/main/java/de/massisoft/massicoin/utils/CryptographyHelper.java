package de.massisoft.massicoin.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

public class CryptographyHelper {
	
	public String generateHash(String data) {
		try {
			
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte [] hash = digest.digest(data.getBytes("UTF-8"));
			
			// get hex values
			return getByteToHex(hash);
	
			
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public KeyPair ellipticCurveCrypto() {
		KeyPairGenerator keyPairGenerator;
		try {
			
			keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
			SecureRandom secrandom = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec params = new ECGenParameterSpec("secp192k1");
			keyPairGenerator.initialize(params, secrandom);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			
			return keyPair;
			
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

		return null;
		
	}

	private static String getByteToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		
		for (byte b : hash) {
			String hexDec = Integer.toHexString(0xff & b);
			
			if (hexDec.length() == 1) {
				hexString.append(0);
			}
			hexString.append(hexDec);
		}
		return hexString.toString();
	}
}
