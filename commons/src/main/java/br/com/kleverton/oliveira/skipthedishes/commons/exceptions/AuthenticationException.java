package br.com.kleverton.oliveira.skipthedishes.commons.exceptions;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() { }

    public AuthenticationException(String message) {
        super( message );
    }

    public AuthenticationException(String message, Throwable cause) {
        super( message, cause );
    }
}