package com.example;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Payload;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public final class JwtUtil {

    private JwtUtil() {
    }

    public static final String SECRET_KEY = "secret_key";

    public static String generateToken(UserDetails userDetails) {
        return createToken(userDetails.getUsername());
    }

    public static Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public static String extractUsername(String token) {
        return extractClaim(token, Payload::getSubject);
    }

    public static boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Payload::getExpiresAt);
    }


    private static String createToken(String subject) {
        return JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 60))
                .sign(HMAC512(SECRET_KEY.getBytes()));
    }

    private static <T> T extractClaim(String token, Function<DecodedJWT, T> claimsResolver) {
        DecodedJWT decodedJWT = JWT.require(HMAC512(SECRET_KEY.getBytes())).build()
                .verify(token);
        return claimsResolver.apply(decodedJWT);
    }
}
