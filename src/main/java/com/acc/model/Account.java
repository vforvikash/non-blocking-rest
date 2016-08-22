package com.acc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vikash Kaushik
 * Simple POJO for Account
 */
@Entity
@Data
@NoArgsConstructor
public class Account {
	@Id
	@GeneratedValue
	private Long id;
	private String accountName;
	
	public Account(String accountName) {
		this.accountName = accountName;
	}
}
