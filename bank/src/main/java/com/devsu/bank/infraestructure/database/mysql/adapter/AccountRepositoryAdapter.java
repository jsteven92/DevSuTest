package com.devsu.bank.infraestructure.database.mysql.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.devsu.bank.domain.model.Account;
import com.devsu.bank.domain.model.repository.AccountRepositoryPort;
import com.devsu.bank.infraestructure.database.mysql.entity.AccountEntity;
import com.devsu.bank.infraestructure.database.mysql.repository.AccountJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepositoryPort {
	
	private final AccountJpaRepository accountJpaRepository;
	@Override
	public Optional<Account> findAccountById(Long id) {
		return accountJpaRepository.findById(id)
				.map(account -> account.toDomainModel());
	}
	
	@Override
	public Account saveAccount(Account account) {
		AccountEntity accountEntity = AccountEntity.fromDomainModel(account);
		return accountJpaRepository.save(accountEntity).toDomainModel();
	}

	@Override
	public List<Account> findAll() {
		return accountJpaRepository.findAll()
				.stream()
				.map(AccountEntity::toDomainModel)
				.collect(Collectors.toList());
	}

}
