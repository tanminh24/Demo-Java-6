package com.store.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
