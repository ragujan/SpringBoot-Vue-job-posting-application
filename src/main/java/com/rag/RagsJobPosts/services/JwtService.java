package com.rag.RagsJobPosts.services;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;

@Service
public class JwtService implements Serializable {
	private static final long serialVersionUID = -2550185165626007488L;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;



	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, "username").toString();
	}

	// retrieve all claims from a token
	private Map<String, Object> getAllClaimsFromToken(String token) {
		// Build an HMC verifier using the same secret that was used to sign the JWT
		Verifier verifier = HMACVerifier.newVerifier(secret);

		// Verify and decode the encoded string JWT to a rich object
		JWT jwt = JWT.getDecoder().decode(token, verifier);
		return jwt.getAllClaims();
	}

	public Object getClaimFromToken(String token, String claim) {
		return getAllClaimsFromToken(token).get(claim);
	}

	// get expriration date from token
	public ZonedDateTime getExpirationDateFromToken(String token) {
		Object object = getAllClaimsFromToken(token).get("exp");
		ZonedDateTime expiryDateTime = (ZonedDateTime) object;
		return expiryDateTime;

	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		final ZonedDateTime expirationDate = getExpirationDateFromToken(token);
		return expirationDate.isAfter(ZonedDateTime.now());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		// Build an HMAC signer using a SHA-256 hash
		Signer signer = HMACSigner.newSHA256Signer(secret);

		// Build a new JWT with an issuer(iss), issued at(iat), subject(sub) and
		// expiration(exp)
		JWT jwt = new JWT().setIssuer("www.ragbag.com").setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
				.setSubject(subject).addClaim("user", claims.get("user")).addClaim("username", claims.get("username"))
				.setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(60));

		String encodedJWT = JWT.getEncoder().encode(jwt, signer);
		return encodedJWT;

	}

	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("user", "admin");
		claims.put("username", username);
		String subject = "testSubject";
		return doGenerateToken(claims, subject);
	}

	public Boolean validateToken(String token, String username) {
		final String retrievedUsername = getUsernameFromToken(token);
		return (username.equals(retrievedUsername) && !isTokenExpired(token));
	}
}
