package br.com.kleverton.oliveira.skipthedishes.account.controllers;

import br.com.kleverton.oliveira.skipthedishes.account.routes.AuthRoutes;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spark.Service;

/**
 * This class is responsible for configure the server. Here we define port and routes for the REST service.
 */

@Controller
public class AuthRoutesController {
    @Autowired
    private AuthRoutes authRoutes;

    /**
     * Configure all routes that wil be used by Auth module.
     */
    public void configureRoutes() {
        Service http = Service.ignite();

        http.port( 6001 );

        http.after( ( (request, response) -> response.type("application/json" ) ) );

        http.post( "/accounts/authenticate", this.authRoutes.authenticate );
        http.post( "/restaurants/authenticate", this.authRoutes.authenticateRestaurant );

        http.post( "*", (req, res) -> {
            res.status( HttpStatus.NOT_FOUND_404 );

            return "{}";
        } );
    }
}