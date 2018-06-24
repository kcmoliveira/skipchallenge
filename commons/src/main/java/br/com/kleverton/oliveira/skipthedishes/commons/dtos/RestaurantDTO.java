package br.com.kleverton.oliveira.skipthedishes.commons.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantDTO extends BaseDTO {
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private List<ProductDTO> productList;

    public RestaurantDTO() { }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ProductDTO> getProductList() {
        this.productList =
                Optional.ofNullable( this.productList ).orElse( new ArrayList<>());

        return this.productList;
    }

    public void setProductList(List<ProductDTO> productList) {
        this.productList = productList;
    }
}