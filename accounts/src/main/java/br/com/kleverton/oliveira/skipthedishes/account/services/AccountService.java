package br.com.kleverton.oliveira.skipthedishes.account.services;

import br.com.kleverton.oliveira.skipthedishes.commons.dtos.AccountDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.exceptions.AccountAlreadyExistsException;
import br.com.kleverton.oliveira.skipthedishes.commons.repositories.AccountRepository;
import br.com.kleverton.oliveira.skipthedishes.commons.security.SecurityUtils;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is responsible implement the details of the services defined by the {@link br.com.kleverton.oliveira.skipthedishes.account.routes.AccountRoutes}
 */

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    /**
     * This method creates a new account into the database.
     * @param accountDTO Contains username and password
     * @return a new instance of {@link AccountDTO} synchronized with the database (with primary key)
     */
    public AccountDTO signUp(AccountDTO accountDTO) {
        try {
            //TODO: validation

            accountDTO.setPassword( SecurityUtils.hashPassword( accountDTO.getPassword() ) );

            return this.accountRepository.save( accountDTO );
        } catch (Exception e) {
            Throwable cause = e.getCause();

            if( cause instanceof MySQLIntegrityConstraintViolationException && cause.getMessage().contains( "Duplicate entry" ) ) {
                throw new AccountAlreadyExistsException( "User already exists." );
            }

            throw new RuntimeException( "Error signing up.", e );
        }
    }
}