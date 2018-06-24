package br.com.kleverton.oliveira.skipthedishes.commons.exceptions;

public class RestaurantAlreadyExistsException extends RuntimeException {
    public RestaurantAlreadyExistsException() { }

    public RestaurantAlreadyExistsException(String message) {
        super( message );
    }

    public RestaurantAlreadyExistsException(String message, Throwable cause) {
        super( message, cause );
    }
}