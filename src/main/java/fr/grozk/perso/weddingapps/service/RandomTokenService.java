package fr.grozk.perso.weddingapps.service;

import java.math.BigInteger;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.grozk.perso.weddingapps.entity.repository.WeddingRepository;

@Service
public class RandomTokenService {

	@Autowired
	WeddingRepository weddingRepository;
	
	public String generateUniqueRandomToken() {
		
	    int length = 5;
	    boolean useLetters = true;
	    boolean useNumbers = false;
	    String generatedString = null;
	    boolean uniqueToken = false;
	    
	    while (!uniqueToken) {
	    	generatedString = RandomStringUtils.random(length, useLetters, useNumbers).toUpperCase();
	 	    
	    	uniqueToken = ((BigInteger) weddingRepository.isTokenAlreadyUsed(generatedString)) == BigInteger.ZERO ? true : false;
	    }
	    
	    return generatedString;
	}
}
