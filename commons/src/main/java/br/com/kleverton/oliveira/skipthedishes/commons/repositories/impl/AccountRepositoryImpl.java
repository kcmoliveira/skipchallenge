package br.com.kleverton.oliveira.skipthedishes.commons.repositories.impl;

import br.com.kleverton.oliveira.skipthedishes.commons.dtos.AccountDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.jooq.tables.Accounts;
import br.com.kleverton.oliveira.skipthedishes.commons.repositories.AccountRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl extends BaseRepositoryImpl<Accounts, AccountDTO> implements AccountRepository {
    public static final Accounts table = Accounts.ACCOUNTS;
    public static final Class<AccountDTO> dtoClass = AccountDTO.class;

    public AccountRepositoryImpl() {
        super( table, dtoClass );
    }

    @Override
    public AccountDTO findByUsername(String username) {
        return this.getDsl().select()
                .from( this.table )
                .where( Accounts.ACCOUNTS.USERNAME.eq( username ) ).fetchOneInto( this.dtoClass );
    }
}