package br.com.kleverton.oliveira.skipthedishes.commons.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

import static br.com.kleverton.oliveira.skipthedishes.commons.security.SecurityConstraints.EXPIRATION_TIME;
import static br.com.kleverton.oliveira.skipthedishes.commons.security.SecurityConstraints.SECRET;

public class SecurityUtils {
    public static boolean isPasswordCorrect(String plainText, String hashed) {
        return BCrypt.checkpw( plainText, hashed );
    }

    public static String hashPassword(String plainText) {
        return new BCryptPasswordEncoder().encode( plainText );
    }

    public static String generateToken(String username) {
        final String jwtToken = Jwts.builder()
                .setSubject( username )
                .setExpiration( new Date( System.currentTimeMillis() + EXPIRATION_TIME ) )
                .signWith( SignatureAlgorithm.HS512, SECRET )
                .compact();

        return jwtToken;
    }
}