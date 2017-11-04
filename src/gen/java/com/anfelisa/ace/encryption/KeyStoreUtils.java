package com.anfelisa.ace.encryption;

import static org.apache.commons.codec.binary.Hex.decodeHex;
import static org.apache.commons.codec.binary.Hex.encodeHex;
import static org.apache.commons.io.FileUtils.readFileToByteArray;
import static org.apache.commons.io.FileUtils.writeStringToFile;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;

public class KeyStoreUtils {
	public static final String ALGO = "AES";

	public static SecretKey generateKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGO);
		SecretKey key = keyGenerator.generateKey();
		return key;
	}

	public static void saveKey(SecretKey key, File file) throws IOException {
		byte[] encoded = key.getEncoded();
		char[] hex = encodeHex(encoded);
		String data = String.valueOf(hex);
		writeStringToFile(file, data);
	}

	public static SecretKey loadKey(File file) throws IOException {
		String data = new String(readFileToByteArray(file));
		char[] hex = data.toCharArray();
		byte[] encoded;
		try {
			encoded = decodeHex(hex);
		} catch (DecoderException e) {
			e.printStackTrace();
			return null;
		}
		SecretKey key = new SecretKeySpec(encoded, ALGO);
		return key;
	}
	
	public static Cipher generateCipher(SecretKey key, int mode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		Cipher cipher = Cipher.getInstance(ALGO);
		cipher.init(mode, key);
		return cipher;
	}
}
