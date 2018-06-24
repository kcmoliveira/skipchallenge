package br.com.kleverton.oliveira.skipthedishes.account.routes;

import br.com.kleverton.oliveira.skipthedishes.account.services.AuthService;
import br.com.kleverton.oliveira.skipthedishes.commons.dtos.AccountDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.dtos.LoginDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.dtos.RestaurantDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.exceptions.AuthenticationException;
import br.com.kleverton.oliveira.skipthedishes.commons.security.SecurityUtils;
import br.com.kleverton.oliveira.skipthedishes.commons.util.JsonUtils;
import br.com.kleverton.oliveira.skipthedishes.commons.ws.ResponseError;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.Response;
import spark.Route;

import static br.com.kleverton.oliveira.skipthedishes.commons.security.SecurityConstraints.AUTHORIZATION_HEADER;
import static br.com.kleverton.oliveira.skipthedishes.commons.security.SecurityConstraints.TOKEN_PREFIX;

/**
 * This class is responsible for define what every route should do. Also prepare the response for the client.
 */


@Service
public class AuthRoutes {
    @Autowired
    private AuthService authService;

    /**
     * Define the authenticate route. Contains a call to {@link AuthService} and prepares the result for the client.
     */
    public final Route authenticate = (req, res) -> {
        try {
            LoginDTO loginDTO = JsonUtils.convertFromJson( req.body(), LoginDTO.class );

            AccountDTO accountDTO = this.authService.authenticate( loginDTO );

            final String jwtToken = SecurityUtils.generateToken( accountDTO.getUsername() );

            res.status( HttpStatus.OK_200 );
            res.header( AUTHORIZATION_HEADER, TOKEN_PREFIX + jwtToken );

            return JsonUtils.convertToPrettyJson( accountDTO );
        } catch (Exception e) {
            return this.handleError( res, e );
        }
    };

    /**
     * Define the authenticate route. Contains a call to {@link AuthService} and prepares the result for the client.
     */
    public final Route authenticateRestaurant = (req, res) -> {
        try {
            RestaurantDTO restaurantDTO = JsonUtils.convertFromJson( req.body(), RestaurantDTO.class );

            restaurantDTO = this.authService.authenticateRestaurant( restaurantDTO );

            final String jwtToken = SecurityUtils.generateToken( restaurantDTO.getName() );

            res.status( HttpStatus.OK_200 );
            res.header( AUTHORIZATION_HEADER, TOKEN_PREFIX + jwtToken );

            return JsonUtils.convertToPrettyJson( restaurantDTO );
        } catch (Exception e) {
            return this.handleError( res, e );
        }
    };

    private String handleError(Response res, Exception e) {
        e.printStackTrace();

        ResponseError response = new ResponseError();

        if (e instanceof AuthenticationException) {
            res.status( HttpStatus.UNAUTHORIZED_401 );

            response.setStatusCode( HttpStatus.UNAUTHORIZED_401 );
            response.setMessage( e.getMessage() );
        } else {
            res.status( HttpStatus.INTERNAL_SERVER_ERROR_500 );

            response.setStatusCode( HttpStatus.INTERNAL_SERVER_ERROR_500 );
            response.setMessage( "Error authenticating." );
        }

        return JsonUtils.convertToPrettyJson( response );
    }
}