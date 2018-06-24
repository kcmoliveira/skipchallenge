package br.com.kleverton.oliveira.skipthedishes.products.services;

import br.com.kleverton.oliveira.skipthedishes.commons.dtos.AccountDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.dtos.OrderDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.dtos.ProductDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.dtos.RestaurantDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.repositories.ProductRepository;
import br.com.kleverton.oliveira.skipthedishes.commons.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is responsible implement the details of the services defined by the
 */

@Service
public class ProductsService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    /**
     * This method creates a new product for the restaurant
     * @param productDTO Contains name, price and the id of the restaurant
     * @return a new instance of {@link ProductDTO} synchronized with database
     */
    public ProductDTO saveProduct(ProductDTO productDTO) {
        //TODO: validation

        return this.productRepository.save( productDTO );
    }

    /**
     * Search for restaurant by name
     * @param username the name of the restaurant
     * @return a new instance of {@link RestaurantDTO}, if found
     */
    public RestaurantDTO findByName(String username) {
        return this.restaurantRepository.findByName( username );
    }
}