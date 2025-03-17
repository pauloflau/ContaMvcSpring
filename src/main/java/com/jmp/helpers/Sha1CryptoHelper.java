package com.jmp.helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1CryptoHelper {
	// Método para receber um valor e retorná-lo criptografado em SHA1
	public static String getSha1Encrypt(String value) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] result = md.digest(value.getBytes());

			// Converte o array de bytes em uma representação hexadecimal
			StringBuilder sb = new StringBuilder();
			for (byte b : result) {
				sb.append(String.format("%02x", b));
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
