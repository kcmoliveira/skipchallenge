package br.com.kleverton.oliveira.skipthedishes.orders.controllers;

import br.com.kleverton.oliveira.skipthedishes.commons.filters.JWTAuthorizationFilter;
import br.com.kleverton.oliveira.skipthedishes.orders.routes.OrdersRoutes;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spark.Service;

/**
 * This class is responsible for configure the server. Here we define port and routes for the REST service.
 */

@Controller
public class OrdersRoutesController {
    @Autowired
    private OrdersRoutes ordersRoutes;

    /**
     * Configure all routes that wil be used by Account module.
     */
    public void configureRoutes() {
        Service http = Service.ignite();

        http.port( 7001 );

        http.after( ( (request, response) -> response.type("application/json" ) ) );

        http.before( "/api/*", JWTAuthorizationFilter::doFilter );

        http.post( "/api/accounts/:username/orders", this.ordersRoutes.orderProduct );

        http.post( "*", (req, res) -> {
            res.status( HttpStatus.NOT_FOUND_404 );

            return "{}";
        } );
    }
}