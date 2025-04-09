package com.devsu.bank.infraestructure.database.mysql.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.devsu.bank.domain.model.Account;
import com.devsu.bank.domain.model.Movement;
import com.devsu.bank.domain.model.enums.AccountStatus;
import com.devsu.bank.domain.model.enums.AccountType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;

	private String accountNumber;

	private AccountType type;

	private BigDecimal inicialBalance;

	private BigDecimal balance;

	private AccountStatus status;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<MovementEntity> movementEntityList;

	private Long clientId;
	private String clientName;

	public Account toDomainModel() {
		List<Movement> movementList = new ArrayList<>();
		if (movementEntityList != null && !movementEntityList.isEmpty()) {
			movementList = movementEntityList.stream().map(MovementEntity::toDomainModel).collect(Collectors.toList());
		}

		return new Account(accountId, accountNumber, type, inicialBalance, balance, status, null, clientId, clientName);
	}

	public static AccountEntity fromDomainModel(Account account) {
		List<MovementEntity> movementList = new ArrayList<>();
		if (account.getMovementList() != null && !account.getMovementList().isEmpty()) {
			movementList = account.getMovementList().stream().map(MovementEntity::fromDomainModel)
					.collect(Collectors.toList());

		}
		return new AccountEntity(account.getAccountId(), account.getAccountNumber(), account.getType(),
				account.getInicialBalance(), account.getBalance(), account.getStatus(), null,
				account.getClientId(),account.getName());
	}
}
