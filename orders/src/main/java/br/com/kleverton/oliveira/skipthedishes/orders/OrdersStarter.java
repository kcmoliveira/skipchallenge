package br.com.kleverton.oliveira.skipthedishes.orders;

import br.com.kleverton.oliveira.skipthedishes.orders.controllers.OrdersRoutesController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This class is responsible for running the module Orders, used for order food.
 */

@Configuration
@ComponentScan( "br.com.kleverton.oliveira.skipthedishes" )
public class OrdersStarter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( OrdersStarter.class );

        OrdersRoutesController ordersRoutesController = context.getBean( OrdersRoutesController.class );
        ordersRoutesController.configureRoutes();
    }
}