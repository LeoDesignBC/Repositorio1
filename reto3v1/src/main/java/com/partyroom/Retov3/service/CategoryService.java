package com.partyroom.Retov3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partyroom.Retov3.model.Category;
import com.partyroom.Retov3.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    //Traera la lista de todas las categorias
    public List<Category> getCategories() {
        return categoryRepository.getCategories();
    }

    //Trae la categoria del id que le pasemos
    public Optional <Category> getCategory(int categoryId){
        return categoryRepository.getCategory(categoryId);
    }

    //  Guarda la informacion de una categoria
    public Category saveCategory(Category category) {
      if(category.getId()==null){
            return categoryRepository.saveCategory(category);
         }else{
             Optional<Category> categoryOptional = categoryRepository.getCategory(category.getId());
            if(categoryOptional.isEmpty()){
                return categoryRepository.saveCategory(category);
            }
            else{
              return category;
           }
            
        }
        
    }

    //Actualiza la informacion de una categoria apoyandose en los metodos anteriores en esta clase y en el modelo de la categoria
    public Category updateCategory(Category category){
        if (category.getId() !=null){ //aqui comprueban que si nos enviaron una categoria y que tome ese id y lo vuelva a la variable g el elemento
            Optional<Category> g= categoryRepository.getCategory(category.getId());
            if(category.getName()!=null){ // es una seguridad de que si los datos ingresados son diferente a un vacio, tome esa informacion
                g.get().setName((category.getName())); //y la actualice, por ese motivo hacemos un if por cada atributo de la entidad
            }
            if(category.getDescription()!=null){
                g.get().setDescription((category.getDescription()));
            }
            if(category.getPartyrooms()!=null){
                g.get().setPartyrooms((category.getPartyrooms()));
            }
            categoryRepository.saveCategory(g.get());
            return g.get();
        }else{
            return category;
        }
    }

    public boolean deleteCategory(int categoryId){
        //iterar por toda la tabla: y buscar el id que le pasamos -> es para generar un loop
        Boolean d= getCategory(categoryId).map(category->{   
            categoryRepository.deleteCategory(category); //si la encuentra borra la categoria con esa id
            return true;
        }).orElse(false);
            return d;
        }
    
}
