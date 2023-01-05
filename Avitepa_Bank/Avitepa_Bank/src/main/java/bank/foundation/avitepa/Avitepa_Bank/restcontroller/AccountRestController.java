package bank.foundation.avitepa.Avitepa_Bank.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bank.foundation.avitepa.Avitepa_Bank.Execption.AccountNotFoundException;
import bank.foundation.avitepa.Avitepa_Bank.Execption.InsufficientFundsException;
import bank.foundation.avitepa.Avitepa_Bank.Execption.NoDataException;
import bank.foundation.avitepa.Avitepa_Bank.entity.Account;
import bank.foundation.avitepa.Avitepa_Bank.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountRestController {
	
	private AccountService accountService;  
	
	@Autowired
	public AccountRestController(AccountService theaccountService) {
		accountService = theaccountService;
	}
	 
	@GetMapping("/account")
	public ResponseEntity<?> findAll() throws NoDataException{
		List<Account> account= accountService.findAllAccount();
		if(!account.isEmpty()) {
			return new ResponseEntity <>(account,HttpStatus.OK);
		}
		else {
			throw new NoDataException("No Account Found");
		}
	}

	@GetMapping("/account/{theaccNo}")
	public ResponseEntity<?> findByAccNo(@PathVariable int theaccNo) throws AccountNotFoundException{
		Account account = accountService.findByAccNo(theaccNo);
		if(account !=null) {
			return new ResponseEntity <>(account,HttpStatus.OK);
		}
		else {		
			throw new AccountNotFoundException("Account not found with given account number");
		}
	}
	
	@GetMapping("/account/accountbalance/{theaccNo}")
	public ResponseEntity<?> accountBalance(@PathVariable int theaccNo) throws AccountNotFoundException {
		Account account=accountService.accountBalance(theaccNo);
		if(account !=null) {
			double balance=account.getAcc_balance();
			return new ResponseEntity<>(balance, HttpStatus.OK);
		}
		else {
			throw new AccountNotFoundException("Account not found with given account number");
		}
	}
	
	@PostMapping("/account/addaccount")
	public ResponseEntity<?> addAccount(@RequestBody Account theaccount) throws HttpMessageNotReadableException {
		Account account = accountService.addAccount(theaccount);
		if (account != null) {
			return new ResponseEntity <>(account,HttpStatus.CREATED);
		}
		else {
			throw new HttpMessageNotReadableException("Please enter a valid input");
		}
	}
	
	@DeleteMapping("/account/delete/{theaccNo}")
	public ResponseEntity<?> deleteAccount(@PathVariable int theaccNo) throws AccountNotFoundException{
		String message=accountService.deleteAccount(theaccNo);
		if(message!= null) {
			return new ResponseEntity<>("Account DELELTED",HttpStatus.OK);
		}
		else {
			throw new AccountNotFoundException("Account not found with given account number");
		}
	}
	
	@PutMapping("/account/update/{theAccNo}")
	public ResponseEntity<?> updateAccount(@PathVariable int theAccNo, @RequestBody Account accountRe ) throws AccountNotFoundException {
		Account account =accountService.updateAccount(theAccNo, accountRe);
		if (account != null) {
			return new ResponseEntity <>(account,HttpStatus.OK);
		}
		else {
			throw new AccountNotFoundException("Account not found with given account number");
		}
	}
	
	@PutMapping("/account/transfer/{senderAccNo}/{reciverAccNo}/{ammount}") 
	public ResponseEntity<?> transaction(@PathVariable int senderAccNo,@PathVariable int reciverAccNo,@PathVariable double ammount) throws InsufficientFundsException, AccountNotFoundException {
		String message = accountService.transaction(senderAccNo, reciverAccNo, ammount);	
		if (message!=null) {
			if(message == "Insufficient Funds")
			throw new InsufficientFundsException("Insufficient funds to transfer");
			else if (message=="Transaction Completed") {
			  return new ResponseEntity<>("Transaction Completed",HttpStatus.OK);
			}	
			else {
				return new ResponseEntity<>("Please enter vaild ammount",HttpStatus.NOT_ACCEPTABLE);
			}
		}
		else {
			throw new AccountNotFoundException("Account not found with given account number" + senderAccNo);
		}
	}
	
	@DeleteMapping("/account/deleteAll")
	public void deleteAllAccount(){
		accountService.deleteAllAccount();
	}
}
