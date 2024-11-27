package com.kcalculator.common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Encrypter {
	
	public final String ENCRYPT_ALGORITHM = "SHA-256";
	
    // SHA-256 + Salt로 해싱(암호화)
    public Map<String, String> generateHash(String rawString) {
    	// salt 생성
    	byte[] salt = generateSalt();
    	
    	try {
    		// SHA-256 알고리즘 사용
    		/*
    		 * MessageDigest 가 thread-safe하지 않다는 글을 봄. getinstance보단 새 인스턴스를 만드는 것이 좋다고 한다?
    		 * 몰라 나중의 내가 알아서 찾아볼 것
    		 */
			MessageDigest digest = MessageDigest.getInstance(ENCRYPT_ALGORITHM);
			
	        // password와 salt를 합침
	        digest.update(salt);
	        byte[] hashedBytes = digest.digest(rawString.getBytes(StandardCharsets.UTF_8));
	        // 결과를 Base64로 반환
	        Map<String, String> result = new HashMap<>();
	        result.put("encryptedString", Base64.getEncoder().encodeToString(hashedBytes));
	        result.put("salt", Base64.getEncoder().encodeToString(salt));
	        
	        return result;
    	} catch(NoSuchAlgorithmException e) {
    		log.info("[### Sha256WithSalt] 에러: {}, 이유: {}", e.getClass(), e.getCause());
    		return null;
    	}
    }

    public boolean matchWithEncryptedString (String raw, String encrypted, String salt) {
        // SHA-256 알고리즘 사용
		/*
		 * MessageDigest 가 thread-safe하지 않다는 글을 봄. getinstance보단 새 인스턴스롤 만드는 것이 좋다고 한다?
		 */
    	byte[] saltBytes = Base64.getDecoder().decode(salt);
    	
    	MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(ENCRYPT_ALGORITHM);
			digest.update(saltBytes);
			byte[] hashedRaw = digest.digest(raw.getBytes(StandardCharsets.UTF_8));
			
			// 해시 전 String를 salt로 해시한 결과가 암호화된 결과와 같은지 여부
			return encrypted.equals(Base64.getEncoder().encodeToString(hashedRaw));
		} catch (NoSuchAlgorithmException e) {
			log.info("[### Sha256WithSalt] 암호화 알고리즘 사용 불가 에러:{}", e.getClass());
			return false;
		}
    }

    
    // SecureRandom으로 안전한 salt 생성
    private byte[] generateSalt() {
        byte[] salt = new byte[16];
        
        // 유닉스 계열 운영체제에서 SecureRandom의 성능이 느릴 수 있다.
        // SecureRandom이 난수를 생성할 때 엔트로피풀을 사용하는데, 기본적으로는 엔트로피가 쌓일 때까지 기다리는 블로킹 풀(blocking-pool)방식을 사용한다.
        // 
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }
}
