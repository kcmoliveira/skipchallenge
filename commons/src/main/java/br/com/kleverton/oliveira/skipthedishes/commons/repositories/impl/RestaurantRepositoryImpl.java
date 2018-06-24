package br.com.kleverton.oliveira.skipthedishes.commons.repositories.impl;

import br.com.kleverton.oliveira.skipthedishes.commons.dtos.RestaurantDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.jooq.tables.Restaurants;
import br.com.kleverton.oliveira.skipthedishes.commons.repositories.RestaurantRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepositoryImpl extends BaseRepositoryImpl<Restaurants, RestaurantDTO> implements RestaurantRepository {
    public static final Restaurants table = Restaurants.RESTAURANTS;
    public static final Class<RestaurantDTO> dtoClass = RestaurantDTO.class;

    public RestaurantRepositoryImpl() {
        super( table, dtoClass );
    }

    @Override
    public RestaurantDTO findByName(String name) {
        return this.getDsl().select()
                .from( this.table )
                .where( Restaurants.RESTAURANTS.NAME.eq( name ) ).fetchOneInto( this.dtoClass );
    }
}