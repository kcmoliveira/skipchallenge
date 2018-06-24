package br.com.kleverton.oliveira.skipthedishes.restaurants.controllers;

import br.com.kleverton.oliveira.skipthedishes.commons.filters.JWTAuthorizationFilter;
import br.com.kleverton.oliveira.skipthedishes.restaurants.routes.RestaurantsRoutes;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spark.Service;

/**
 * This class is responsible for configure the server. Here we define port and routes for the REST service.
 */

@Controller
public class RestaurantsRoutesController {
    @Autowired
    private RestaurantsRoutes restaurantsRoutes;

    /**
     * Configure all routes that wil be used by Account module.
     */
    public void configureRoutes() {
        Service http = Service.ignite();

        http.port( 9001 );

        http.after( ( (request, response) -> response.type("application/json" ) ) );

        http.before( "/api/*", JWTAuthorizationFilter::doFilter );

        http.post( "/restaurants", this.restaurantsRoutes.createRestaurant );

        http.post( "*", (req, res) -> {
            res.status( HttpStatus.NOT_FOUND_404 );

            return "{}";
        } );
    }
}