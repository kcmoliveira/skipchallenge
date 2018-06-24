package br.com.kleverton.oliveira.skipthedishes.restaurants;

import br.com.kleverton.oliveira.skipthedishes.restaurants.controllers.RestaurantsRoutesController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This class is responsible for running the module Restaurants, used for creating restaurants.
 */

@Configuration
@ComponentScan( "br.com.kleverton.oliveira.skipthedishes" )
public class RestaurantsStarter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( RestaurantsStarter.class );

        RestaurantsRoutesController restaurantsRoutesController = context.getBean( RestaurantsRoutesController.class );
        restaurantsRoutesController.configureRoutes();
    }
}