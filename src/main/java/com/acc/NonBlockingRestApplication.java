package com.acc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.acc.model.Account;
import com.acc.repo.AccountRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Enables and implements spring reactive web application
 * @author vikash.kaushik
 *
 */
@SpringBootApplication
public class NonBlockingRestApplication {
	
	private static final Logger log = LoggerFactory.getLogger(NonBlockingRestApplication.class);
	
	/**
	 * command line runner to initiate some data
	 * @param rr
	 * @return
	 */
	@Bean CommandLineRunner runner(AccountRepository rr){
		return args -> {
			Flux.just("Saro,Ashwani,Satya,Bhargav,Vikash,Avinash,Janki,Karthik,Ram".split(","))
			.flatMap(accName -> 
				Mono.just(new Account(accName)).subscribeOn(Schedulers.parallel())
				)
			.subscribe(account -> {
				  rr.save(account);
				  log.info("Consume this value: " + account.toString());
				});
		};
	}

	/**
	 * This starts the booted application
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(NonBlockingRestApplication.class, args);
	}
}
