package bank.foundation.avitepa.Avitepa_Bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.foundation.avitepa.Avitepa_Bank.entity.Account;
import bank.foundation.avitepa.Avitepa_Bank.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	private AccountRepository accountRepository;
	
	@Autowired
	public AccountServiceImpl(AccountRepository theaccountRepository) {
		accountRepository=theaccountRepository;
	}
		
	@Override
	public List<Account> findAllAccount() {
		return accountRepository.findAll();
	}

	@Override
	public Account findByAccNo(int theaccNo) {
		Optional<Account> account = accountRepository.findById(theaccNo);
		if (account.isPresent()) {
			return account.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Account accountBalance(int theaccNo) {
		Optional<Account> account = accountRepository.findById(theaccNo);
		if (account.isPresent()) {
			return account.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Account addAccount(Account theAccount) {
		Account account= accountRepository.save(theAccount);
		return account;
	}

	@Override
	public String deleteAccount(int theaccNo) {
		Optional<Account> account = accountRepository.findById(theaccNo);
		if (account.isPresent()) {
		    accountRepository.deleteById(theaccNo);
			return "Account deleted for AccNo: " +theaccNo ;
		}
		else {
			return null;
		}

	}

	@Override
	public String transaction(int senderAccNo, int reciverAccNo, double ammount) {
		Optional<Account> sender = accountRepository.findById(senderAccNo);
		Optional<Account> reciver = accountRepository.findById(reciverAccNo);
		Account theSender;
		Account theReciver;
		if(sender.isPresent() && reciver.isPresent() && !sender.equals(reciver)) {
			theSender=sender.get();
			theReciver=reciver.get();
			if(theSender.getAcc_balance() > ammount && theSender.getAcc_balance() > 0 ) {
				if (ammount!=0 && ammount>0) {
					theSender.setAcc_balance(theSender.getAcc_balance()- ammount);
					accountRepository.save(theSender);
					theReciver.setAcc_balance(theReciver.getAcc_balance() + ammount);
					accountRepository.save(theReciver);
					return "Transaction Completed";
				}
				else {
					return "Please enter valid ammount";
				}
			}
			else {
				return "Insufficient Funds";
			}
		}
		else {
			return null;
		}
		
	}

	@Override
	public String deleteAllAccount() {
		accountRepository.deleteAll();
		return "All account deleted";
	}

	@Override
	public Account updateAccount(int theAccNo, Account accountRe) {
		Account account;
		Optional<Account> theAccount = accountRepository.findById(theAccNo);
		if (theAccount.isPresent()) {
				account=theAccount.get();
				account.setAccountType(accountRe.getAccountType());
				account.setAcc_balance(accountRe.getAcc_balance());
				return accountRepository.save(account);
		}
		else {
			return null;
		}
	}

}
