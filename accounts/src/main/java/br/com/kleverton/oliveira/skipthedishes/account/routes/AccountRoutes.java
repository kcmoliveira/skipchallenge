package br.com.kleverton.oliveira.skipthedishes.account.routes;

import br.com.kleverton.oliveira.skipthedishes.account.services.AccountService;
import br.com.kleverton.oliveira.skipthedishes.commons.dtos.AccountDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.exceptions.AccountAlreadyExistsException;
import br.com.kleverton.oliveira.skipthedishes.commons.util.JsonUtils;
import br.com.kleverton.oliveira.skipthedishes.commons.ws.ResponseError;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.Route;

/**
 * This class is responsible for define what every route should do. Also prepare the response for the client.
 */

@Service
public class AccountRoutes {
    @Autowired
    private AccountService accountService;

    /**
     * Define the sign up route. Contains a call to {@link AccountService} and prepares the result for the client.
     */
    public final Route signup = (req, res) -> {
        try {
            AccountDTO accountDTO = JsonUtils.convertFromJson( req.body(), AccountDTO.class );

            accountDTO = this.accountService.signUp( accountDTO );

            res.status( HttpStatus.CREATED_201 );

            return JsonUtils.convertToPrettyJson( accountDTO );
        } catch (Exception e) {
            e.printStackTrace();

            ResponseError response = new ResponseError();

            if (e instanceof AccountAlreadyExistsException) {
                res.status( HttpStatus.CONFLICT_409 );

                response.setStatusCode( HttpStatus.CONFLICT_409 );
                response.setMessage( e.getMessage() );
            } else {
                res.status( HttpStatus.INTERNAL_SERVER_ERROR_500 );

                response.setStatusCode( HttpStatus.INTERNAL_SERVER_ERROR_500 );
                response.setMessage( "Error signing up." );
            }

            return JsonUtils.convertToPrettyJson( response );
        }
    };
}