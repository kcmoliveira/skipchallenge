package br.com.kleverton.oliveira.skipthedishes.orders.test;

import br.com.kleverton.oliveira.skipthedishes.account.AuthStarter;
import br.com.kleverton.oliveira.skipthedishes.commons.dtos.OrderDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.dtos.OrderProductDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.util.JsonUtils;
import br.com.kleverton.oliveira.skipthedishes.orders.OrdersStarter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;

import static br.com.kleverton.oliveira.skipthedishes.commons.security.SecurityConstraints.AUTHORIZATION_HEADER;

public class OrdersRestTest {
    public static final String AUTHENTICATE = "http://localhost:6001/accounts/authenticate";
    public static final String ORDERS = "http://localhost:7001/api/accounts/USER/orders";

    private String username = "kleverton.oliveira";
    private String loginJson = "{\"username\" : \"" + username +  "\",\"password\" : \"123456\"}";

    private OrderDTO orderDTO;

    @BeforeClass
    public static void beforeClassTest() {
        AuthStarter.main( new String[] { } );
        OrdersStarter.main( new String[] { } );
    }

    @Before
    public void beforeTest() {
        this.orderDTO = new OrderDTO();

        this.orderDTO.setIdRestaurant( 1L );
        this.orderDTO.setDateOrder( LocalDateTime.now() );

        OrderProductDTO whooper = new OrderProductDTO();
        whooper.setIdProduct( 1L );
        whooper.setQuantity( 1L );

        OrderProductDTO chickenCrisp = new OrderProductDTO();
        chickenCrisp.setIdProduct( 2L );
        chickenCrisp.setQuantity( 2L );

        this.orderDTO.getOrderProductsList().add( whooper );
        this.orderDTO.getOrderProductsList().add( chickenCrisp );
    }

    @Test
    public void orderProductSuccessTest() throws Exception {
        HttpResponse<JsonNode> loginResponse = Unirest.post( AUTHENTICATE )
                .header("accept", "application/json")
                .body( this.loginJson )
                .asJson();

        System.out.println( String.format( "Login: %s", loginResponse.getBody() ) );

        Assert.assertEquals( HttpStatus.OK_200, loginResponse.getStatus() );
        Assert.assertNotNull( loginResponse.getHeaders() );
        Assert.assertFalse( loginResponse.getHeaders().isEmpty() );
        Assert.assertNotNull( loginResponse.getHeaders().getFirst( AUTHORIZATION_HEADER ) );

        String token = loginResponse.getHeaders().getFirst( AUTHORIZATION_HEADER );

        String orderJson = JsonUtils.convertToPrettyJson( this.orderDTO );

        HttpResponse<JsonNode> orderResponse = Unirest.post( ORDERS.replace( "USER", this.username) )
                .header("accept", "application/json" )
                .header( AUTHORIZATION_HEADER, token )
                .body( orderJson )
                .asJson();

        System.out.println( String.format( "Order: %s", orderResponse.getBody() ) );

        Assert.assertEquals( HttpStatus.CREATED_201, orderResponse.getStatus() );
    }

    @Test
    public void orderProductFailsTest() throws Exception {
        String orderJson = JsonUtils.convertToPrettyJson( this.orderDTO );

        HttpResponse<JsonNode> response = Unirest.post( ORDERS )
                .header("accept", "application/json")
                .body( orderJson )
                .asJson();

        System.out.println( response.getBody() );

        Assert.assertEquals( HttpStatus.UNAUTHORIZED_401, response.getStatus() );
    }
}