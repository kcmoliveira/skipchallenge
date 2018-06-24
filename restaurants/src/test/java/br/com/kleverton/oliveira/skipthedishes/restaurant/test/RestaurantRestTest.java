package br.com.kleverton.oliveira.skipthedishes.restaurant.test;

import br.com.kleverton.oliveira.skipthedishes.commons.repositories.RestaurantRepository;
import br.com.kleverton.oliveira.skipthedishes.restaurants.RestaurantsStarter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith( SpringRunner.class )
@ContextConfiguration( classes = RestaurantRestTest.class )
@ComponentScan( "br.com.kleverton.oliveira.skipthedishes" )
public class RestaurantRestTest {
    public static final String CREATE_RESTAURANT = "http://localhost:9001/restaurants";

    @Autowired
    private RestaurantRepository restaurantRepository;

    private String name = "Skip restaurant" + System.currentTimeMillis();
    private String restaurantJson = "{\"name\" : \"" + name +  "\",\"password\" : \"123456\"}";

    @BeforeClass
    public static void beforeClassTest() {
        RestaurantsStarter.main( new String[] { } );
    }

//    private void deleteRestaurant() {
//        RestaurantDTO byName = this.restaurantRepository.findByName( this.name);
//
//        Optional.ofNullable( byName ).ifPresent( this.restaurantRepository::delete );
//    }

    @Test
    public void createRestaurantTest() throws Exception {
//        this.deleteRestaurant();

        HttpResponse<JsonNode> response = Unirest.post(CREATE_RESTAURANT)
                .header("accept", "application/json")
                .body( this.restaurantJson)
                .asJson();

        Assert.assertEquals( HttpStatus.CREATED_201, response.getStatus() );
        Assert.assertNotNull( response.getBody() );
    }

    @Test
    public void createRestaurantFailedTest() throws Exception {
        HttpResponse<JsonNode> response = Unirest.post(CREATE_RESTAURANT)
                .header("accept", "application/json")
                .body( this.restaurantJson)
                .asJson();

        Assert.assertEquals( HttpStatus.CONFLICT_409, response.getStatus() );
        Assert.assertNotNull( response.getBody() );

//        this.deleteRestaurant();
    }
}