package org.coupons.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.coupons.pojo.Role;
import org.coupons.security.pojo.JWTComtentType;
import org.coupons.security.pojo.TokenContainer;
import org.coupons.util.Constants;

import com.google.gson.JsonObject;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderValues;
import io.undertow.util.Headers;

public class AuthHelper {

	private static final String SIGNING_KEY = "secret";

	// private static final String SIGNING_KEY =
	// "LongAndHardToGuessValueWithSpecialCharacters@^($%*$%";

	public static String getTokenContainer(final String refreshToken, final String accessToken,
			final long accessTokenExpirationTime) throws Exception {

		String signedRefreshToken = signingTokenSH256(refreshToken);
		String signedAccessToken = signingTokenSH256(accessToken);
		return new TokenContainer(signedRefreshToken, signedAccessToken, accessTokenExpirationTime).toJsonString();
	}

	public static String signingTokenSH256(String token) throws Exception {
		String hash = signHS256(token);

		return "Bearer " + token + "." + hash;
	}

	private static String signHS256(String token) throws NoSuchAlgorithmException, InvalidKeyException {
		final String algName = Constants.SIGNING_ALGORITHM_FULL_NAME;
		Mac sha256_HMAC = Mac.getInstance(algName);
		SecretKeySpec secret_key = new SecretKeySpec(SIGNING_KEY.getBytes(), algName);
		sha256_HMAC.init(secret_key);

		String hash = Base64.getUrlEncoder().withoutPadding().encodeToString(sha256_HMAC.doFinal(token.getBytes()));
		return hash;
	}

	public static String createToken(JsonObject header, JsonObject payload) throws Exception {

		byte[] serializedHeader = header.toString().getBytes("UTF-8");
		String encodedHeader = Base64.getUrlEncoder().withoutPadding().encodeToString(serializedHeader);
		byte[] serializedPayload = payload.toString().getBytes("UTF-8");
		String encodedPayload = Base64.getUrlEncoder().withoutPadding().encodeToString(serializedPayload);
		return encodedHeader + "." + encodedPayload;
	}

	public static JsonObject createTokenHeader(JWTComtentType cty) {

		JsonObject header = new JsonObject();

		// kid
		header.addProperty("kid", "100");
		
		// type
		header.addProperty("typ", "JWT");
		
		// signature algorithm
		header.addProperty("alg", Constants.SIGNING_ALGORITHM_NAME);
		
		// content type
		header.addProperty("cty", cty.name());

		// "jti" (JWT ID) Claim 
		header.addProperty("jti", UUID.randomUUID().toString());

		return header;
	}

	public static JsonObject createTokenPayload(String userId, Role userRole, String iss, long durationMinutes,
			Map<String, String> otherClaims) {

		Instant accessExperationTime = Instant.now().plus(durationMinutes, ChronoUnit.MINUTES);
		long accessExperationTimeInSeconds = Instant.ofEpochSecond(0L).until(accessExperationTime,
                ChronoUnit.SECONDS);
		
		Set<String> keys = otherClaims.keySet();

		JsonObject payload = new JsonObject();

		payload.addProperty("userId", userId);
		payload.addProperty("role", userRole.name());
		// issuer
		payload.addProperty("iss", iss);
		// expiration time
		payload.addProperty("exp", accessExperationTimeInSeconds);
		// issued at
		payload.addProperty("iat", Instant.ofEpochSecond(0L).until(Instant.now(),
                ChronoUnit.SECONDS));

		for (String key : keys) {
			payload.addProperty(key, otherClaims.get(key));
		}

		return payload;

	}

	public static boolean verifyToken(final HttpServerExchange exchange) throws Exception {
		HeaderValues  headerValues = exchange.getRequestHeaders().get(Headers.AUTHORIZATION);
		String[]  arr = headerValues.getLast().split(" ");
		String token = arr[1];
		String[] spliteratedToken = token.split("\\.");
		String header = spliteratedToken[0];
		String payload = spliteratedToken[1];
		String signature = spliteratedToken[2];
		String newSignature = signHS256(header + "." + payload);
		return signature.equals(newSignature);
	}
	
	/**
	 * Verifies a json web token validity and extracts the user id and other
	 * information from it.
	 * 
	 * @param token
	 * @return
	 * @throws SignatureException
	 * @throws InvalidKeyException
	 */
//	 public static TokenInfo verifyToken(String token) {
//	 try {
//	 final Verifier hmacVerifier = new HmacSHA256Verifier(SIGNING_KEY.getBytes());
//	
//	 VerifierProvider hmacLocator = new VerifierProvider() {
//	
//	 @Override
//	 public List<Verifier> findVerifier(String id, String key) {
//	 return Lists.newArrayList(hmacVerifier);
//	 }
//	 };
//	 VerifierProviders locators = new VerifierProviders();
//	 locators.setVerifierProvider(SignatureAlgorithm.HS256, hmacLocator);
//	 net.oauth.jsontoken.Checker checker = new net.oauth.jsontoken.Checker() {
//	
//	 @Override
//	 public void check(JsonObject payload) throws SignatureException {
//	 // don't throw - allow anything
//	 }
//	
//	 };
//	 // Ignore Audience does not mean that the Signature is ignored
//	 JsonTokenParser parser = new JsonTokenParser(locators, checker);
//	 JsonToken jt;
//	 try {
//	 jt = parser.verifyAndDeserialize(token);
//	 } catch (SignatureException e) {
//	 throw new RuntimeException(e);
//	 }
//	 JsonObject payload = jt.getPayloadAsJsonObject();
//	 TokenInfo t = new TokenInfo();
//	 String issuer = payload.getAsJsonPrimitive("iss").getAsString();
//	 String userIdString =
//	 payload.getAsJsonObject("info").getAsJsonPrimitive("userId").getAsString();
//	 if (issuer.equals(ISSUER) && !StringUtils.isBlank(userIdString)) {
//	 t.setUserId(userIdString);
//	 t.setIssued(new DateTime(payload.getAsJsonPrimitive("iat").getAsLong()));
//	 t.setExpires(new DateTime(payload.getAsJsonPrimitive("exp").getAsLong()));
//	 return t;
//	 } else {
//	 return null;
//	 }
//	 } catch (InvalidKeyException e1) {
//	 throw new RuntimeException(e1);
//	 }
//	 }

}
