package br.com.kleverton.oliveira.skipthedishes.commons.dtos;

public class ProductDTO extends BaseDTO {
    private String name;
    private double price;
    private Long idRestaurant;

    public ProductDTO() { }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getIdRestaurant() {
        return this.idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
}