package br.com.kleverton.oliveira.skipthedishes.commons.ws;

public class ResponseError {
    private int statusCode;
    private String message;

    public ResponseError() { }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}