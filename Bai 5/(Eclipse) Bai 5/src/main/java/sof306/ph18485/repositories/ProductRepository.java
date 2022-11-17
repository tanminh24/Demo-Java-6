package sof306.ph18485.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<sof306.ph18485.entities.Product, Integer> {
	

}