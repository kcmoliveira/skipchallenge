package br.com.kleverton.oliveira.skipthedishes.account;

import br.com.kleverton.oliveira.skipthedishes.account.controllers.AuthRoutesController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This class is responsible for running the module Auth, used for authentication.
 */

@Configuration
@ComponentScan( "br.com.kleverton.oliveira.skipthedishes" )
public class AuthStarter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( AuthStarter.class );

        AuthRoutesController authController = context.getBean( AuthRoutesController.class );

        authController.configureRoutes();
    }
}