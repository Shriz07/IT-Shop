package com.example.projekt.repository;

import com.example.projekt.model.OrderedProduct;
import org.springframework.data.repository.CrudRepository;

public interface OrderedProductRepository extends CrudRepository<OrderedProduct, Integer>
{

}
