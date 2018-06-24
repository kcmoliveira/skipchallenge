package br.com.kleverton.oliveira.skipthedishes.products.routes;

import br.com.kleverton.oliveira.skipthedishes.commons.dtos.ProductDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.dtos.RestaurantDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.util.JsonUtils;
import br.com.kleverton.oliveira.skipthedishes.commons.ws.ResponseError;
import br.com.kleverton.oliveira.skipthedishes.products.services.ProductsService;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.Route;

/**
 * This class is responsible for define what every route should do. Also prepare the response for the client.
 */

@Service
public class ProductsRoutes {
    @Autowired
    private ProductsService productsService;

    /**
     *  Define the product route. Contains a call to {@link ProductsService} and prepares the result for the client.
     */
    public final Route createProduct = (req, res) -> {
        try {
            String restaurant = req.params( "restaurant" );

            RestaurantDTO restaurantDTO = this.productsService.findByName( restaurant );

            ProductDTO productDTO = JsonUtils.convertFromJson( req.body(), ProductDTO.class );

            productDTO.setIdRestaurant( restaurantDTO.getId() );

            productDTO = this.productsService.saveProduct( productDTO );

            res.status( HttpStatus.CREATED_201 );

            return JsonUtils.convertToPrettyJson( productDTO );
        } catch (Exception e) {
            e.printStackTrace();

            ResponseError response = new ResponseError();

            res.status( HttpStatus.INTERNAL_SERVER_ERROR_500 );

            response.setStatusCode( HttpStatus.INTERNAL_SERVER_ERROR_500 );
            response.setMessage( "Error creating product." );

            return JsonUtils.convertToPrettyJson( response );
        }
    };
}