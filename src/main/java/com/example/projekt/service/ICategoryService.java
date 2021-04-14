package com.example.projekt.service;

import com.example.projekt.model.Category;

import java.util.List;

public interface ICategoryService
{
    public List<Category> findAll();

    public String findCategoryName(Integer id);
}
