package br.com.kleverton.oliveira.skipthedishes.commons.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDTO extends BaseDTO {
    private Long idRestaurant;
    private Long idAccount;
    private LocalDateTime dateOrder;
    private List<OrderProductDTO> orderProductsList;

    public OrderDTO() { }

    public Long getIdRestaurant() {
        return this.idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Long getIdAccount() {
        return this.idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public LocalDateTime getDateOrder() {
        return this.dateOrder;
    }

    public void setDateOrder(LocalDateTime dateOrder) {
        this.dateOrder = dateOrder;
    }

    public List<OrderProductDTO> getOrderProductsList() {
        this.orderProductsList =
                Optional.ofNullable( this.orderProductsList ).orElse( new ArrayList<>() );

        return this.orderProductsList;
    }

    public void setOrderProductsList(List<OrderProductDTO> orderProductsList) {
        this.orderProductsList = orderProductsList;
    }
}