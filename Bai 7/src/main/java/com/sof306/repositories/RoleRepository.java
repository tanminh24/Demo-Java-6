package com.sof306.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sof306.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
