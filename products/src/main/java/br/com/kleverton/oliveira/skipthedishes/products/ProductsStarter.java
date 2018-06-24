package br.com.kleverton.oliveira.skipthedishes.products;

import br.com.kleverton.oliveira.skipthedishes.products.controllers.ProductsRoutesController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This class is responsible for running the module Products, used for saving the restaurant's products.
 */

@Configuration
@ComponentScan( "br.com.kleverton.oliveira.skipthedishes" )
public class ProductsStarter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( ProductsStarter.class );

        ProductsRoutesController productsRoutesController = context.getBean( ProductsRoutesController.class );
        productsRoutesController.configureRoutes();
    }
}