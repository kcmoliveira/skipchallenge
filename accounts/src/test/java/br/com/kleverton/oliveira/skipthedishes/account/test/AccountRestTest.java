package br.com.kleverton.oliveira.skipthedishes.account.test;

import br.com.kleverton.oliveira.skipthedishes.account.AccountStarter;
import br.com.kleverton.oliveira.skipthedishes.commons.dtos.AccountDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.repositories.AccountRepository;
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

import java.util.Optional;

@RunWith( SpringRunner.class )
@ContextConfiguration( classes = AccountRestTest.class )
@ComponentScan( "br.com.kleverton.oliveira.skipthedishes" )
public class AccountRestTest {
    public static final String SIGN_UP = "http://localhost:5001/accounts/signup";

    @Autowired
    private AccountRepository accountRepository;

    private String username = "kleverton.oliveira";
    private String accountJson = "{\"username\" : \"" + username +  "\",\"password\" : \"123456\"}";

    @BeforeClass
    public static void beforeClassTest() {
        AccountStarter.main( new String[] { } );

//        Spark.awaitInitialization();
    }

    @Test
    public void signUpTest() throws Exception {
        AccountDTO byUsername = this.accountRepository.findByUsername( this.username );

        Optional.ofNullable( byUsername ).ifPresent( this.accountRepository::delete );

        HttpResponse<JsonNode> response = Unirest.post(SIGN_UP)
                .header("accept", "application/json")
                .body( this.accountJson )
                .asJson();

        Assert.assertEquals( HttpStatus.CREATED_201, response.getStatus() );
        Assert.assertNotNull( response.getBody() );
    }

    @Test
    public void signUpFailedTest() throws Exception {
        HttpResponse<JsonNode> response = Unirest.post(SIGN_UP)
                .header("accept", "application/json")
                .body( this.accountJson )
                .asJson();

        Assert.assertEquals( HttpStatus.CONFLICT_409, response.getStatus() );
        Assert.assertNotNull( response.getBody() );
    }
}