package com.devsu.bank.domain.model.repository;

import java.util.List;
import java.util.Optional;

import com.devsu.bank.domain.model.Movement;

public interface MovementRepositoryPort {
	Optional<Movement> findMovementById(Long id);
	Movement saveMovement(Movement movement);
	List<Movement> findMovementByAccountId(Long accoundId);
}
