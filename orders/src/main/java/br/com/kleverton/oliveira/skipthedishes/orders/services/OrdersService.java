package br.com.kleverton.oliveira.skipthedishes.orders.services;

import br.com.kleverton.oliveira.skipthedishes.commons.dtos.AccountDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.dtos.OrderDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.dtos.OrderProductDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.repositories.AccountRepository;
import br.com.kleverton.oliveira.skipthedishes.commons.repositories.OrderProductRepository;
import br.com.kleverton.oliveira.skipthedishes.commons.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is responsible implement the details of the services defined by the {@link br.com.kleverton.oliveira.skipthedishes.orders.routes.OrdersRoutes}
 */

@Service
public class OrdersService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private AccountRepository accountRepository;

    /**
     * This method create a new order into database.
     * @param orderDTO Contains account, restaurant and a list of products
     * @return a new instance of {@link OrderDTO} synchronized with database
     */
    public OrderDTO orderProduct(OrderDTO orderDTO) {
        //TODO: validation

        OrderDTO orderSaved = this.orderRepository.save( orderDTO );

        orderDTO.getOrderProductsList().forEach( productDTO -> {
            productDTO.setIdOrder( orderSaved.getId() );

            OrderProductDTO productSaved = this.orderProductRepository.save( productDTO );

            orderSaved.getOrderProductsList().add( productSaved );
        } );

        return orderSaved;
    }

    /**
     * This method search for {@link AccountDTO} by username
     * @param username unique key that identifies the account
     * @return a new instance of {@link AccountDTO} synchronized with database
     */
    public AccountDTO findByUsername(String username) {
        return this.accountRepository.findByUsername( username );
    }
}