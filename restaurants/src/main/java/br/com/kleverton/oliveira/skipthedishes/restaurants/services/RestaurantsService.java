package br.com.kleverton.oliveira.skipthedishes.restaurants.services;

import br.com.kleverton.oliveira.skipthedishes.commons.dtos.RestaurantDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.exceptions.RestaurantAlreadyExistsException;
import br.com.kleverton.oliveira.skipthedishes.commons.repositories.RestaurantRepository;
import br.com.kleverton.oliveira.skipthedishes.commons.security.SecurityUtils;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is responsible implement the details of the services defined by the
 */

@Service
public class RestaurantsService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    /**
     * This method creates a new restaurant into database.
     * @param restaurantDTO Contains the name of the restaurant
     * @return a new instance of {@link RestaurantDTO} synchronized with database
     */
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        try {
            //TODO: validation

            restaurantDTO.setPassword( SecurityUtils.hashPassword( restaurantDTO.getPassword() ) );

            return this.restaurantRepository.save( restaurantDTO );
        } catch (Exception e) {
            Throwable cause = e.getCause();

            if( cause instanceof MySQLIntegrityConstraintViolationException && cause.getMessage().contains( "Duplicate entry" ) ) {
                throw new RestaurantAlreadyExistsException( "Restaurant already exists." );
            }

            throw new RuntimeException( "Error signing up.", e );
        }
    }
}