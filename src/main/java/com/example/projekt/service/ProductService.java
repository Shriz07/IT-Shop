package com.example.projekt.service;
import com.example.projekt.model.Product;
import com.example.projekt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService
{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public List<Product> findAllInCategory(Integer id) {
        return productRepository.findAllInCategory(id);
    }

    public List<Product> findAllWithSpecs(Specification<Product> specs) { return productRepository.findAll(Specification.where(specs)); }

    public Product findById(Integer id) { return productRepository.findById(id).get(); }

    public void deleteById(Integer id) { productRepository.deleteById(id); }

    @Override
    public Page<Product> findProductsOnPage(int pageNo, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.productRepository.findAll(pageable);
    }


    public void addProduct(Product product, String filename, Integer brandId, Integer categoryId)
    {
        product.setImage(filename);
        product.setBrandId(brandId);
        product.setCategoryId(categoryId);
        productRepository.save(product);
    }

    public void updateProduct(Product product, Integer categoryId, Integer brandId)
    {
        product.setCategoryId(categoryId);
        product.setBrandId(brandId);
        productRepository.save(product);
    }
}
