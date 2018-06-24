package br.com.kleverton.oliveira.skipthedishes.orders.routes;

import br.com.kleverton.oliveira.skipthedishes.commons.dtos.AccountDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.dtos.OrderDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.util.JsonUtils;
import br.com.kleverton.oliveira.skipthedishes.commons.ws.ResponseError;
import br.com.kleverton.oliveira.skipthedishes.orders.services.OrdersService;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.Route;

/**
 * This class is responsible for define what every route should do. Also prepare the response for the client.
 */


@Service
public class OrdersRoutes {
    @Autowired
    private OrdersService ordersService;

    /**
     *  Define the order product route. Contains a call to {@link OrdersService} and prepares the result for the client.
     */
    public final Route orderProduct = (req, res) -> {
        try {
            String username = req.params( "username" );

            AccountDTO accountDTO = this.ordersService.findByUsername( username );

            OrderDTO orderDTO = JsonUtils.convertFromJson( req.body(), OrderDTO.class );

            orderDTO.setIdAccount( accountDTO.getId() );

            orderDTO = this.ordersService.orderProduct( orderDTO );

            res.status( HttpStatus.CREATED_201 );

            return JsonUtils.convertToPrettyJson( orderDTO );
        } catch (Exception e) {
            e.printStackTrace();

            ResponseError response = new ResponseError();

            res.status( HttpStatus.INTERNAL_SERVER_ERROR_500 );

            response.setStatusCode( HttpStatus.INTERNAL_SERVER_ERROR_500 );
            response.setMessage( "Error creating order." );

            return JsonUtils.convertToPrettyJson( response );
        }
    };
}