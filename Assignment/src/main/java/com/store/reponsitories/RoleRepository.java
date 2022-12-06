package com.store.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
