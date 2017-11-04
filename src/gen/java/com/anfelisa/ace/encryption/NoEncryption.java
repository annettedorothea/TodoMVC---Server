package com.anfelisa.ace.encryption;

import java.io.File;

public class NoEncryption implements IEncryptionService {

	public void initKey(File file) {
	}

	public String encrypt(String string) {
		return string;
	}

	public String decrypt(String string) {
		return string;
	}

}

