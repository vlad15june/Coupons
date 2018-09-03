package org.coupons.security;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.coupons.pojo.Role;
import org.coupons.security.pojo.JSONWebToken;
import org.coupons.security.pojo.JWTComtentType;
import org.coupons.security.pojo.SignatureAlgorithm;
import org.coupons.security.pojo.TokenHeader;
import org.coupons.security.pojo.TokenPayload;

public class ApiSecurityExample {
  public static void main(String[] args) {
    try {
     String secret = "secret";
     String message = "Message";

     Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
     SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
     sha256_HMAC.init(secret_key);

     String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
     System.out.println(hash);
     
     TokenHeader header = new TokenHeader(SignatureAlgorithm.HS256, JWTComtentType.ACCESS_TOKEN);
     TokenPayload payload = new TokenPayload("userId", Role.ADMIN, "nick.org", Instant.now(), new URI("https://www.coupons.org"));
     JSONWebToken token = new JSONWebToken(header, payload);
     
    System.out.println(signingToken(SignatureAlgorithm.HS256, token));
    }
    catch (Exception e){
     System.out.println("Error");
    }
   }
  
  public static String signingToken(SignatureAlgorithm alg, JSONWebToken token) throws Exception{
	    String secret = "secret";
	     String message = "Message";

	     Mac sha256_HMAC = Mac.getInstance(alg.getFullName());
	     SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), alg.getFullName());
	     sha256_HMAC.init(secret_key);

	     String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(token.toJWTFormatedString().getBytes()));	  
	     return hash;
}
  
}