package br.com.kleverton.oliveira.skipthedishes.restaurants.routes;

import br.com.kleverton.oliveira.skipthedishes.commons.dtos.RestaurantDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.exceptions.RestaurantAlreadyExistsException;
import br.com.kleverton.oliveira.skipthedishes.commons.util.JsonUtils;
import br.com.kleverton.oliveira.skipthedishes.commons.ws.ResponseError;
import br.com.kleverton.oliveira.skipthedishes.restaurants.services.RestaurantsService;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.Route;

/**
 * This class is responsible for define what every route should do. Also prepare the response for the client.
 */

@Service
public class RestaurantsRoutes {
    @Autowired
    private RestaurantsService restaurantsService;

    /**
     *  Define the restaurant route. Contains a call to {@link RestaurantsService} and prepares the result for the client.
     */
    public final Route createRestaurant = (req, res) -> {
        try {
            RestaurantDTO restaurantDTO = JsonUtils.convertFromJson( req.body(), RestaurantDTO.class );

            restaurantDTO = this.restaurantsService.createRestaurant( restaurantDTO );

            res.status( HttpStatus.CREATED_201 );

            return JsonUtils.convertToPrettyJson( restaurantDTO );
        } catch (Exception e) {
            e.printStackTrace();

            ResponseError response = new ResponseError();

            if (e instanceof RestaurantAlreadyExistsException) {
                res.status( HttpStatus.CONFLICT_409 );

                response.setStatusCode( HttpStatus.CONFLICT_409 );
                response.setMessage( e.getMessage() );
            } else {
                res.status( HttpStatus.INTERNAL_SERVER_ERROR_500 );

                response.setStatusCode( HttpStatus.INTERNAL_SERVER_ERROR_500 );
                response.setMessage( "Error creating restaurant." );
            }

            return JsonUtils.convertToPrettyJson( response );
        }
    };
}