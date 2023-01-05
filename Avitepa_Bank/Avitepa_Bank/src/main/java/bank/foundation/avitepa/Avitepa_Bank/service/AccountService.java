package bank.foundation.avitepa.Avitepa_Bank.service;

import java.util.List;
import bank.foundation.avitepa.Avitepa_Bank.entity.Account;


public interface AccountService {
	
	public List<Account> findAllAccount();
	
	public Account findByAccNo(int theaccNo);
	
	public Account accountBalance(int theaccNo);
	
	public Account addAccount(Account theAccount);
	
	public String deleteAccount(int theaccNo);
	
	public Account updateAccount(int theAccNo, Account theAccount);
	
	public String transaction(int senderAccNo, int reciverAccNo, double ammount);
	
	public String deleteAllAccount();	

}
