package br.com.kleverton.oliveira.skipthedishes.commons.repositories;

import br.com.kleverton.oliveira.skipthedishes.commons.dtos.ProductDTO;

public interface ProductRepository extends BaseRespository<ProductDTO> {
    ProductDTO findByName(String name);
    void deleteProductsByRestaurant(Long idRestaurant);
}