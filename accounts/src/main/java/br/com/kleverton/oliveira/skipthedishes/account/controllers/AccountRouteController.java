package br.com.kleverton.oliveira.skipthedishes.account.controllers;

import br.com.kleverton.oliveira.skipthedishes.account.routes.AccountRoutes;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spark.Service;

/**
 * This class is responsible for configure the server. Here we define port and routes for the REST service.
 */

@Controller
public class AccountRouteController {
    @Autowired
    private AccountRoutes accountRoutes;

    /**
     * Configure all routes that wil be used by Account module.
     */
    public void configureRoutes() {
        Service http = Service.ignite();

        http.port( 5001 );

        http.after( ( (request, response) -> response.type("application/json" ) ) );

        http.post( "/accounts/signup", this.accountRoutes.signup );

        http.post( "*", (req, res) -> {
            res.status( HttpStatus.NOT_FOUND_404 );

            return "{}";
        } );
    }
}
