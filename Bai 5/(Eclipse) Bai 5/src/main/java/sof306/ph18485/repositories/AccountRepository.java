package sof306.ph18485.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sof306.ph18485.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	
}
