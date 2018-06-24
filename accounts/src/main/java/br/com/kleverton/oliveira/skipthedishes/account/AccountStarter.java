package br.com.kleverton.oliveira.skipthedishes.account;

import br.com.kleverton.oliveira.skipthedishes.account.controllers.AccountRouteController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This class is responsible for running the module Account, used for creating accounts.
 */

@Configuration
@ComponentScan( "br.com.kleverton.oliveira.skipthedishes" )
public class AccountStarter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( AccountStarter.class );

        AccountRouteController  accountRouteController = context.getBean( AccountRouteController.class );
        accountRouteController.configureRoutes();
    }
}
