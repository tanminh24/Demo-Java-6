package sof306.ph18485.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sof306.ph18485.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
