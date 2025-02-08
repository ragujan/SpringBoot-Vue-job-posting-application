package com.rag.RagsJobPosts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.aot.DisabledInAotMode;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisabledInNativeImage
@DisabledInAotMode
@SpringBootTest
class JwtTests {
	@Value("${jwt.secret}")
	// private String secret2;

	@Test
	void contextLoads() {
		String secret = "ragbag";
		// String token =
		// "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.Uer6o8NwLY279BFBCZsKPBcRidBwfeiLhbxX6zfKdA4";

		// Build an HMC verifier using the same secret that was used to sign the JWT
		Verifier verifier = HMACVerifier.newVerifier(secret);

		// Verify and decode the encoded string JWT to a rich object
		JWT jwt = JWT.getDecoder().decode(token, verifier);
		assertEquals(jwt.subject, "1234567890");
		// assertEquals("javainuse", secret2);

	}

	String secret = "ragbag";
	String email = "abc@gmail.com";

	// retrieve all claims from a token
	private Map<String, Object> getAllClaimsFromToken(String token) {

		// Build an HMC verifier using the same secret that was used to sign the JWT
		Verifier verifier = HMACVerifier.newVerifier(secret);

		// Verify and decode the encoded string JWT to a rich object
		JWT jwt = JWT.getDecoder().decode(generateToken(email), verifier);
		return jwt.getAllClaims();
	}

	String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJleHBpcnkiOiIyMDI1LTAxLTAxIDAwOjAwOjAwIn0.OnDFK5RexMNg3t71Jok-WdxAoxcd0gTtZX7BBoQchTc";

	@Test
	void testClaim() {

		Object object = getAllClaimsFromToken(token).get("name");
		if(object !=null){
			assertEquals(object.toString(), "John Doe");
		}
	}

	String jsonString = "{" + "\"sub\": \"1234567890\"," + "\"name\": \"John Doe\"," + "\"iat\": 1516239022,"
			+ "\"expiry\": 2025" + "}";

	@Test
	void testExpiry() {
		Map<String, Object> map = getAllClaimsFromToken(generateToken("abc@gmail.com"));
		String lookingFor = "exp";
		System.out.println(" map is " + map);
		if (map.containsKey(lookingFor)) {
			assertTrue(map.containsKey(lookingFor));
		} else {
			throw new AssertionError(map);
		}

	}

	@Test
	void testExpiryValid() {
			ZonedDateTime expiryDate = getExpirationDateFromToken(generateToken(email));
			ZonedDateTime now = ZonedDateTime.now();
			assertTrue(expiryDate.isAfter(now));

	}

	// get expriration date from token
	ZonedDateTime getExpirationDateFromToken(String token) {
		Object object = getAllClaimsFromToken(token).get("exp");
		ZonedDateTime expiryDateTime = (ZonedDateTime) object;
		return expiryDateTime;

	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		// Build an HMAC signer using a SHA-256 hash
		Signer signer = HMACSigner.newSHA256Signer(secret);

		// Build a new JWT with an issuer(iss), issued at(iat), subject(sub) and
		// expiration(exp)
		JWT jwt = new JWT().setIssuer("www.ragbag.com").setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
				.setSubject(subject).addClaim("user", claims.get("user")).addClaim("email", claims.get("email"))
				.setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(60));

		String encodedJWT = JWT.getEncoder().encode(jwt, signer);
		return encodedJWT;

	}

	String generateToken(String email) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("user", "admin");
		claims.put("email", email);
		String subject = "testSubject";
		return doGenerateToken(claims, subject);
	}

	@Test
	void testTokenGeneration() {
		String token = generateToken("abc@gmail.com");
		Verifier verifier = HMACVerifier.newVerifier(secret);
		JWT jwt = JWT.getDecoder().decode(token, verifier);
		Map<String, Object> claims = jwt.getAllClaims();
		assertEquals("admin", claims.get("user").toString());
	}

	@Test
	void testEmailFromToken() {
		Map<String, Object> claims = getAllClaimsFromToken(generateToken("abc@gmail.com"));
		assertEquals("abc@gmail.com", claims.get("email").toString());
	}

	@Test
	void testDoThis() {
		assertEquals("hello", "hello");

	}

}
