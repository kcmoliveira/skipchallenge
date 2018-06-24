package br.com.kleverton.oliveira.skipthedishes.auth.test;

import br.com.kleverton.oliveira.skipthedishes.account.AuthStarter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static br.com.kleverton.oliveira.skipthedishes.commons.security.SecurityConstraints.AUTHORIZATION_HEADER;

public class AuthRestTest {
    public static final String AUTHENTICATE = "http://localhost:6001/accounts/authenticate";

    private String loginJson = "{\"username\" : \"kleverton.oliveira\",\"password\" : \"123456\"}";
    private String loginFailJson = "{\"username\" : \"kleverton.macedo\",\"password\" : \"123456\"}";

    @BeforeClass
    public static void beforeClassTest() {
        AuthStarter.main( new String[] { } );
    }

    @Test
    public void authenticateTest() throws Exception {
        HttpResponse<JsonNode> response = Unirest.post( AUTHENTICATE )
                .header("accept", "application/json")
                .body( this.loginJson )
                .asJson();

        Assert.assertEquals( HttpStatus.OK_200, response.getStatus() );
        Assert.assertNotNull( response.getHeaders() );
        Assert.assertFalse( response.getHeaders().isEmpty() );
        Assert.assertNotNull( response.getHeaders().getFirst( AUTHORIZATION_HEADER ) );
    }

    @Test
    public void authenticateFailedTest() throws Exception {
        HttpResponse<JsonNode> response = Unirest.post( AUTHENTICATE )
                .header("accept", "application/json")
                .body( this.loginFailJson )
                .asJson();

        Assert.assertEquals( HttpStatus.UNAUTHORIZED_401, response.getStatus() );
    }
}