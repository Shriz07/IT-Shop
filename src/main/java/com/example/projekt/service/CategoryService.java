package com.example.projekt.service;
import com.example.projekt.model.Category;
import com.example.projekt.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService
{
    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> findAll() {
        return (List<Category>) repository.findAll();
    }

    @Override
    public String findCategoryName(Integer id) {
        Optional<Category> cat = repository.findById(id);
        return cat.get().getName();
    }
}
