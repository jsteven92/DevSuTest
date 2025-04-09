package com.devsu.bank.infraestructure.database.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.bank.infraestructure.database.mysql.entity.AccountEntity;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long>{

}
