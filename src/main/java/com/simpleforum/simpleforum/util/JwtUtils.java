package com.simpleforum.simpleforum.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtils {
    private static final String SECRET = "secret";

    private static final int EXPIRATION_HOURS = 168;

    private static final String ISSUER = "SimpleForum";

    public static String getToken(Map<String, String> claims) {
        JWTCreator.Builder builder = JWT.create();
        claims.forEach(builder::withClaim);
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR, EXPIRATION_HOURS);
        return builder.withExpiresAt(instance.getTime())
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(SECRET)).withIssuer(ISSUER).build().verify(token);
    }

    public static DecodedJWT getDecodedJWT(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).withIssuer(ISSUER).build().verify(token);
    }

    public static Map<String, Claim> getPayload(String token) {
        return getDecodedJWT(token).getClaims();
    }
}
