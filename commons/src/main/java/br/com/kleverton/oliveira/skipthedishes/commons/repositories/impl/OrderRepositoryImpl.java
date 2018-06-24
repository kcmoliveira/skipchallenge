package br.com.kleverton.oliveira.skipthedishes.commons.repositories.impl;

import br.com.kleverton.oliveira.skipthedishes.commons.dtos.OrderDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.jooq.tables.Orders;
import br.com.kleverton.oliveira.skipthedishes.commons.repositories.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl extends BaseRepositoryImpl<Orders, OrderDTO> implements OrderRepository {
    public static final Orders table = Orders.ORDERS;
    public static final Class<OrderDTO> dtoClass = OrderDTO.class;

    public OrderRepositoryImpl() {
        super( table, dtoClass );
    }
}