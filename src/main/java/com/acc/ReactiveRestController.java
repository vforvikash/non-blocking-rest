package com.acc;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acc.model.Account;
import com.acc.model.MessagePojo;
import com.acc.repo.AccountRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author vikash.kaushik
 * This is my first reactive rest service.
 */
@RestController
@RequestMapping("/reactive")
public class ReactiveRestController {
	
	/**
	 * This is currently blocking repository
	 */
	private AccountRepository repository;
	
	/**
	 * @param repository
	 */
	@Autowired
	public ReactiveRestController(AccountRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * @return Message to user
	 */
	@GetMapping("/")
	public Mono<MessagePojo> sayHelloReactiveRest(){
		return Mono.just(new MessagePojo("my_first_message", "Say hello to reactive REST World!!!"));
	}
	
	/**
	 * Usage: http://localhost:8080/reactive/account?accountId=1
	 * Request-Method: GET
	 * Header: Content-type:application/json
	 * @param id
	 * @return Account
	 */
	@GetMapping(value="/account")
	public Mono<Account> getAccount(@RequestParam("accountId") String id){
		if(StringUtils.isNumber(id)){
			return Mono.just(repository.findOne(Long.valueOf(id)));
		}else{
			return null;
		}
	}

	/**
	 * Usage: http://localhost:8080/reactive/account?accountName=Name
	 * Request-Method: GET
	 * Header: Content-type:application/json
	 * @param accountName
	 * @return Account
	 */
	@GetMapping(value="/account/like")
	public Flux<Account> getAccountUsingName(@RequestParam("accountName") String accountName){
		return Flux.fromIterable(repository.findByAccountNameIgnoreCaseContaining(accountName)).log();
	}
	
	/**
	 * URL: http://localhost:8080/reactive/account/all
	 * Request-Method: GET
	 * Header: Content-type:application/json
	 * @return sequence of Account
	 */
	@GetMapping("/account/all")
	public Flux<Account> getAllAccounts(){
		return Flux.fromIterable(repository.findAll()).log();
	}
	
	/**
	 * Usage: 
	 *  URL: http://localhost:8080/reactive/account/new
	 *  Request-Method: POST
	 *  Header: Content-type:application/json
	 *  RequestBody: {
					"id": 0,
					"accountName": "New Name"
					}
	 * @param monoAcc
	 * @return empty stream 
	 */
	@PostMapping("/account/new")
	public Mono<Void> newAccount(@RequestBody(required=true) Mono<Account> monoAcc){
		return monoAcc.log()
			.doOnNext(account -> {repository.save(new Account(account.getAccountName()));}).then();
	}
	
	/**
	 * Usage: 
	 *  URL: http://localhost:8080/reactive/account/update
	 *  Request-Method: POST
	 *  Header: Content-type:application/json
	 *  RequestBody: {
					"id": 1,
					"accountName": "New Name"
					}
	 * @param monoAcc
	 * @return empty stream 
	 */
	@PostMapping("/account/update")
	public Mono<Void> updateAccount(@RequestBody(required=true) Mono<Account> monoAcc){
		return monoAcc.log()
			.doOnNext(account -> {
				if(repository.exists(account.getId())){
					repository.save(account);
				}
			}).then();
	}
}
