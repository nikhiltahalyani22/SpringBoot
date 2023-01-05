package bank.foundation.avitepa.Avitepa_Bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.foundation.avitepa.Avitepa_Bank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
