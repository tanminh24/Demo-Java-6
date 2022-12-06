package com.store.reponsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.store.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN ('ADMIN','STAFF')")
	List<Account> getAdministrators();

	@Query("SELECT acc FROM Account acc WHERE acc.username = :username")
	Account findByUsername(@Param("username") String username);

	@Query("SELECT acc FROM Account acc WHERE acc.token = :token")
	Account findAccByToken(@Param("token") String token);

}
