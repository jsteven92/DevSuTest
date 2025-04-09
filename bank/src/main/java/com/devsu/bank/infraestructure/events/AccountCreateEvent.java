package com.devsu.bank.infraestructure.events;

import com.devsu.bank.domain.model.Account;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountCreateEvent extends Event<Account>{

}
