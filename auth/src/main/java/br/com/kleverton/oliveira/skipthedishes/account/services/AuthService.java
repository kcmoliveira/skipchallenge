package br.com.kleverton.oliveira.skipthedishes.account.services;

import br.com.kleverton.oliveira.skipthedishes.commons.dtos.AccountDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.dtos.LoginDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.dtos.RestaurantDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.exceptions.AuthenticationException;
import br.com.kleverton.oliveira.skipthedishes.commons.repositories.AccountRepository;
import br.com.kleverton.oliveira.skipthedishes.commons.repositories.RestaurantRepository;
import br.com.kleverton.oliveira.skipthedishes.commons.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is responsible implement the details of the services defined by the {@link br.com.kleverton.oliveira.skipthedishes.account.routes.AuthRoutes}
 */

@Service
public class AuthService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    /**
     * This method checks if an account exists and if a valid password was provided.
     * @param loginDTO Contains username and password
     * @return a related instance of {@link AccountDTO} from the database
     */
    public AccountDTO authenticate(LoginDTO loginDTO) {
        try {
            //TODO: validation

            final AccountDTO fromDatabase = this.accountRepository.findByUsername( loginDTO.getUsername() );

            if( fromDatabase == null ) {
                throw new AuthenticationException( "User not found." );
            }

            if ( ! SecurityUtils.isPasswordCorrect( loginDTO.getPassword(), fromDatabase.getPassword() ) ) {
                throw new AuthenticationException( "Bad credentials." );
            }

            return fromDatabase;
        } catch (Exception e) {
            if( e instanceof  AuthenticationException ) {
                throw e;
            }

            throw new RuntimeException( "Error authenticating.", e );
        }
    }

    /**
     * This method checks if a restaurant exists and if a valid password was provided.
     * @param restaurantDTO Contains name and password
     * @returna related instance of {@link RestaurantDTO} from the database
     */
    public RestaurantDTO authenticateRestaurant(RestaurantDTO restaurantDTO) {
        try {
            //TODO: validation

            final RestaurantDTO fromDatabase = this.restaurantRepository.findByName( restaurantDTO.getName() );

            if( fromDatabase == null ) {
                throw new AuthenticationException( "Restaurant not found." );
            }

            if ( ! SecurityUtils.isPasswordCorrect( restaurantDTO.getPassword(), fromDatabase.getPassword() ) ) {
                throw new AuthenticationException( "Bad credentials." );
            }

            return fromDatabase;
        } catch (Exception e) {
            if( e instanceof  AuthenticationException ) {
                throw e;
            }

            throw new RuntimeException( "Error authenticating.", e );
        }
    }
}