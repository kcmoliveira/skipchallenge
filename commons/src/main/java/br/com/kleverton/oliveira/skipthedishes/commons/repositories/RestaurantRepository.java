package br.com.kleverton.oliveira.skipthedishes.commons.repositories;

import br.com.kleverton.oliveira.skipthedishes.commons.dtos.RestaurantDTO;

public interface RestaurantRepository extends BaseRespository<RestaurantDTO> {
    RestaurantDTO findByName(String name);
}