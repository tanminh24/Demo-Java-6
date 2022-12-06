package com.store.reponsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.store.entities.Account;
import com.store.entities.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

	@Query("SELECT DISTINCT a FROM Authority a WHERE a.account IN :accounts")
	List<Authority> authoritiesOf(@Param("accounts") List<Account> accounts);

}
