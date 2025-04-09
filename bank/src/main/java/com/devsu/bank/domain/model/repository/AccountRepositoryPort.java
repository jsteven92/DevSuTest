package com.devsu.bank.domain.model.repository;

import java.util.List;
import java.util.Optional;

import com.devsu.bank.domain.model.Account;

public interface AccountRepositoryPort {
	Optional<Account> findAccountById(Long id);
	Account saveAccount(Account account);
	List<Account> findAll();
	
}
