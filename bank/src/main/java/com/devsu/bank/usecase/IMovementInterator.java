package com.devsu.bank.usecase;

import java.util.List;
import java.util.Optional;

import com.devsu.bank.domain.model.Movement;

public interface IMovementInterator {
	Optional<Movement> findMovById(Long id);
	Movement saveMov(Movement movement);
	List<Movement> findMovementByAccountId(Long accoundId);
}
