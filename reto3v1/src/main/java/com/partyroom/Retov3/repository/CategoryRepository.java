package com.partyroom.Retov3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.partyroom.Retov3.model.Category;
import com.partyroom.Retov3.repository.crudRepository.CategoryCrudRepository;

@Repository
public class CategoryRepository {
    
    @Autowired
    private CategoryCrudRepository categoryCrudRepository;

    public List<Category> getCategories() {
        return (List<Category>) categoryCrudRepository.findAll();
    }

    public Optional<Category> getCategory(int id){
        return categoryCrudRepository.findById(id);
    }

    public Category saveCategory(Category category) {
        return categoryCrudRepository.save(category);
    }

    public void deleteCategory(Category category){
        categoryCrudRepository.delete(category);
    }
}
