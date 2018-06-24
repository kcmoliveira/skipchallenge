package br.com.kleverton.oliveira.skipthedishes.commons.repositories;

import br.com.kleverton.oliveira.skipthedishes.commons.dtos.AccountDTO;

public interface AccountRepository extends BaseRespository<AccountDTO> {
    AccountDTO findByUsername(String username);
}