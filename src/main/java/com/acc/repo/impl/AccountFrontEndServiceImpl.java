package com.acc.repo.impl;

import org.springframework.stereotype.Service;

import com.acc.model.Account;
import com.acc.repo.AccountFrontEndService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author vikash.kaushik
 * TODO: Front end services should be non-blocking
 */
@Service
public class AccountFrontEndServiceImpl implements AccountFrontEndService {
	
	@Override
	public Mono<Account> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Account> findByNameLike(String accountName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> saveAccount(Account account) {
		Mono.just(account);
		return null;
	}

	@Override
	public Mono<Void> updateAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

}
