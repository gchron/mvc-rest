package com.myjetbrains.cronix.repositories;

import com.myjetbrains.cronix.api.v1.model.CategoryDTO;
import com.myjetbrains.cronix.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
}
