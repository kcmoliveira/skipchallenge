package br.com.kleverton.oliveira.skipthedishes.commons.security;

public class SecurityConstraints {
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String SECRET = "nAnDapArbAT";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
}
