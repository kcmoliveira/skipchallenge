package br.com.kleverton.oliveira.skipthedishes.commons.repositories.impl;

import br.com.kleverton.oliveira.skipthedishes.commons.dtos.OrderProductDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.jooq.tables.OrdersProducts;
import br.com.kleverton.oliveira.skipthedishes.commons.repositories.OrderProductRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderProductRepositoryImpl extends BaseRepositoryImpl<OrdersProducts, OrderProductDTO> implements OrderProductRepository {
    public static final OrdersProducts table = OrdersProducts.ORDERS_PRODUCTS;
    public static final Class<OrderProductDTO> dtoClass = OrderProductDTO.class;

    public OrderProductRepositoryImpl() {
        super( table, dtoClass );
    }
}