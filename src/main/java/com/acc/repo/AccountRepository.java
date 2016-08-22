package com.acc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acc.model.Account;

/**
 * Blocking repository :-(
 * TODO: breaking this blocking repository into non-blocking repository.
 * @author vikash.kaushik
 *
 */
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	List<Account> findByAccountNameIgnoreCaseContaining(String accountName);
	
	/*
	//Below are required Non-blocking repository methods
	 
	Mono<Account> findById(Long id);

	Flux<Account> findByNameLike(String accountName);
	
	Flux<Account> findAll();
	
	Mono<Void> saveAccount(Mono<Account> monoAccount);

	Mono<Void> updateAccount(Mono<Account> monoAccount);
	
	*/
}
