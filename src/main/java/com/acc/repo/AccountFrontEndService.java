package com.acc.repo;

import com.acc.model.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountFrontEndService {
	
	Mono<Account> findById(Long id);

	Flux<Account> findByNameLike(String accountName);
	
	Flux<Account> findAll();
	
	Mono<Void> saveAccount(Account account);

	Mono<Void> updateAccount(Account account);
}
