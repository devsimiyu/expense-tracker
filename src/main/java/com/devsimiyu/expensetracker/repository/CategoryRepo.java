package com.devsimiyu.expensetracker.repository;

import java.util.List;

import com.devsimiyu.expensetracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    
    @Query(value = "SELECT * FROM categorylist()", nativeQuery = true)
    List<Category> getCategoryList();

    @Query(value = "SELECT * FROM categoryfind(:id)", nativeQuery = true)
    Category findCategoryById(@Param("id") Integer id);

    @Query(value = "CALL categorysave(:name)", nativeQuery = true)
    Integer saveCategory(@Param("name") String name);
}
