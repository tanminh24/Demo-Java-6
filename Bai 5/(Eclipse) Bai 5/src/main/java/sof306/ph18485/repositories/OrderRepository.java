package sof306.ph18485.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sof306.ph18485.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
