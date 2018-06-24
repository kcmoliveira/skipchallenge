package br.com.kleverton.oliveira.skipthedishes.products.test;

import br.com.kleverton.oliveira.skipthedishes.account.AuthStarter;
import br.com.kleverton.oliveira.skipthedishes.commons.dtos.ProductDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.dtos.RestaurantDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.util.JsonUtils;
import br.com.kleverton.oliveira.skipthedishes.products.ProductsStarter;
import br.com.kleverton.oliveira.skipthedishes.restaurants.RestaurantsStarter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static br.com.kleverton.oliveira.skipthedishes.commons.security.SecurityConstraints.AUTHORIZATION_HEADER;

@RunWith( SpringRunner.class )
@ContextConfiguration( classes = ProductsRestTest.class )
@ComponentScan( "br.com.kleverton.oliveira.skipthedishes" )
public class ProductsRestTest {
    public static final String AUTHENTICATE = "http://localhost:6001/restaurants/authenticate";
    public static final String CREATE_RESTAURANT = "http://localhost:9001/restaurants";
    public static final String CREATE_PRODUCT = "http://localhost:8001/api/restaurants/RESTAURANT_NAME/products";

//    @Autowired
//    private RestaurantRepository restaurantRepository;
//
//    @Autowired
//    private ProductRepository productRepository;

    private String name = "Skip restaurant" + System.currentTimeMillis();
    private String restaurantJson = "{\"name\" : \"" + name +  "\",\"password\" : \"123456\"}";

    private ProductDTO productDTO;

    @BeforeClass
    public static void beforeClassTest() {
        AuthStarter.main( new String[] { } );
        RestaurantsStarter.main( new String[] { } );
        ProductsStarter.main( new String[] { } );
    }

    @Before
    public void beforeTest() throws Exception {
        HttpResponse<JsonNode> response = Unirest.post(CREATE_RESTAURANT)
                .header("accept", "application/json")
                .body( this.restaurantJson)
                .asJson();

        Assert.assertEquals( HttpStatus.CREATED_201, response.getStatus() );
        Assert.assertNotNull( response.getBody() );

        RestaurantDTO byName = JsonUtils.convertFromJson( response.getBody().toString(), RestaurantDTO.class );

        this.productDTO = new ProductDTO();
        this.productDTO.setName( "Hamburger" );
        this.productDTO.setPrice( 15 );
        this.productDTO.setIdRestaurant( byName.getId() );
    }

    @Test
    public void createProductTest() throws Exception {
        HttpResponse<JsonNode> loginResponse = Unirest.post( AUTHENTICATE )
                .header("accept", "application/json")
                .body( this.restaurantJson )
                .asJson();

        System.out.println( String.format( "Login: %s", loginResponse.getBody() ) );

        Assert.assertEquals( HttpStatus.OK_200, loginResponse.getStatus() );
        Assert.assertNotNull( loginResponse.getHeaders() );
        Assert.assertFalse( loginResponse.getHeaders().isEmpty() );
        Assert.assertNotNull( loginResponse.getHeaders().getFirst( AUTHORIZATION_HEADER ) );

        String token = loginResponse.getHeaders().getFirst( AUTHORIZATION_HEADER );

        String orderJson = JsonUtils.convertToPrettyJson( this.productDTO );

        HttpResponse<JsonNode> productResponse = Unirest.post( CREATE_PRODUCT.replace( "RESTAURANT_NAME", this.name ) )
                .header("accept", "application/json" )
                .header( AUTHORIZATION_HEADER, token )
                .body( orderJson )
                .asJson();

        System.out.println( String.format( "Product: %s", productResponse.getBody() ) );

        Assert.assertEquals( HttpStatus.CREATED_201, productResponse.getStatus() );
    }
}