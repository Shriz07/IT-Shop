package com.example.projekt.service;
import com.example.projekt.model.Category;
import com.example.projekt.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public String findCategoryName(Integer id) {
        Optional<Category> cat = categoryRepository.findById(id);
        return cat.get().getName();
    }

    public int addCategory(Category category)
    {
        try {
            categoryRepository.save(category);
        }
        catch (ConstraintViolationException cve)
        {
            return 1;
        }
        catch (DataIntegrityViolationException dive)
        {
            return 2;
        }
        catch (Exception e)
        {
            return 3;
        }
        return 0;
    }
}
