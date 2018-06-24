package br.com.kleverton.oliveira.skipthedishes.commons.dtos;

public class OrderProductDTO extends BaseDTO {
    private Long idOrder;
    private Long idProduct;
    private double quantity;

    public OrderProductDTO() { }

    public Long getIdOrder() {
        return this.idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdProduct() {
        return this.idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}