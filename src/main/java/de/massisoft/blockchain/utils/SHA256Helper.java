package de.massisoft.blockchain.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Helper implements IHasher{

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
