package com.example.projekt.service;

import com.example.projekt.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService
{
    List<Product> findAll();

    List<Product> findAllInCategory(Integer id);

    Page<Product> findProductsOnPage(int pageNo, int pageSize);
}
