package com.anfelisa.ace.encryption;

import java.io.File;

public interface IEncryptionService {
	void initKey(File file);
	String encrypt(String string);
	String decrypt(String string);
}

