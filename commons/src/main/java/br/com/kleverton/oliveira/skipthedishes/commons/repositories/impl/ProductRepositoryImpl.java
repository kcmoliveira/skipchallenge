package br.com.kleverton.oliveira.skipthedishes.commons.repositories.impl;

import br.com.kleverton.oliveira.skipthedishes.commons.dtos.ProductDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.jooq.tables.Products;
import br.com.kleverton.oliveira.skipthedishes.commons.repositories.ProductRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl extends BaseRepositoryImpl<Products, ProductDTO> implements ProductRepository {
    public static final Products table = Products.PRODUCTS;
    public static final Class<ProductDTO> dtoClass = ProductDTO.class;

    public ProductRepositoryImpl() {
        super( table, dtoClass );
    }

    @Override
    public ProductDTO findByName(String name) {
        return this.getDsl().select()
                .from(this.table)
                .where(Products.PRODUCTS.NAME.eq(name)).fetchOneInto(this.dtoClass);
    }

    @Override
    public void deleteProductsByRestaurant(Long idRestaurant) {
        this.getDsl().delete( this.table ).where( this.table.ID_RESTAURANT + " = " + idRestaurant ).execute();
    }
}