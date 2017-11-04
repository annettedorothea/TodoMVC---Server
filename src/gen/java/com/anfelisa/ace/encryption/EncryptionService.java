package com.anfelisa.ace.encryption;

public class EncryptionService {

	private static IEncryptionService encryptionService = new NoEncryption();

	public static void setEncryptionService(IEncryptionService encryptionService) {
		EncryptionService.encryptionService = encryptionService;
	}

	public static String encrypt(String string) {
		return encryptionService.encrypt(string);
	}
	
	public static  String decrypt(String string) {
		return encryptionService.decrypt(string);
	}


}

