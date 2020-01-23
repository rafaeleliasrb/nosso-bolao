package com.dev.eficiente.nosso.bolao.infrastructure.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

//TODO substituir por BCrypt qunado adicionar spring-security
@Component
public class CriptografiaUtil {

	public String criptografar(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			return DatatypeConverter
					.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			throw new CriptografiaException("Não foi possível criptografar a senha", e);
		}
	}
}
