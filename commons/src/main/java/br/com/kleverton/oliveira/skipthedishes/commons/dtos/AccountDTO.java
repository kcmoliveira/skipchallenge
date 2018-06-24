package br.com.kleverton.oliveira.skipthedishes.commons.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDTO extends BaseDTO {
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public AccountDTO() { }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}