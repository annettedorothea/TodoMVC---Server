package com.anfelisa.ace.encryption;

import static com.anfelisa.ace.encryption.KeyStoreUtils.generateCipher;
import static com.anfelisa.ace.encryption.KeyStoreUtils.loadKey;

import java.io.File;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class AESEncryption implements IEncryptionService {

	static final Logger LOG = LoggerFactory.getLogger(AESEncryption.class);

    private SecretKey key;
	private Cipher encryptCipher;
	private Cipher decryptCipher;

	public void initKey(File file) {
		try {
			key = loadKey(file);
			encryptCipher = generateCipher(key, Cipher.ENCRYPT_MODE);
			decryptCipher = generateCipher(key, Cipher.DECRYPT_MODE);
		} catch (Exception e) {
			LOG.error("error in initKey", e);
		}
	}

	public String encrypt(String string) {
		if (StringUtils.isEmpty(string)) {
			return "";
		}
		try {
			byte[] bytes = encryptCipher.doFinal(string.getBytes());
			BASE64Encoder myEncoder = new BASE64Encoder();
			return myEncoder.encode(bytes);
		} catch (Exception x) {
			LOG.error("error in encrypt", x);
			return string;
		}
	}

	public String decrypt(String string) {
		if (StringUtils.isEmpty(string)) {
			return "";
		}
		try {
			BASE64Decoder myDecoder = new BASE64Decoder();
			byte[] crypted = myDecoder.decodeBuffer(string);
			byte[] recoveredBytes = decryptCipher.doFinal(crypted);
			return new String(recoveredBytes);
		} catch (Exception x) {
			LOG.error("error in decrypt", x);
			return string;
		}
	}

}

