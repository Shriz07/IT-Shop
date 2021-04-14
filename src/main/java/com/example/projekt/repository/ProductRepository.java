package com.example.projekt.repository;

import com.example.projekt.model.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Integer>, JpaSpecificationExecutor<Product>
{
    @Query("SELECT p FROM Product p WHERE p.categoryId = ?1")
    List<Product> findAllInCategory(Integer id);
}
