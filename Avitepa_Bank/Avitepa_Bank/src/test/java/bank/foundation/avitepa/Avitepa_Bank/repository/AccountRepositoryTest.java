package bank.foundation.avitepa.Avitepa_Bank.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import bank.foundation.avitepa.Avitepa_Bank.AvitepaBankApplication;
import bank.foundation.avitepa.Avitepa_Bank.entity.Account;




@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = AvitepaBankApplication.class)
public class AccountRepositoryTest {
   
	
	@MockBean
	Account account ;
	
    @Autowired
    AccountRepository accountRepository;
   
   @BeforeEach
	void setUp()  {

	    account = getAccount();
		
	}
   
   @Test
   public void testFindById() {
      Account account = getAccount();	     
      accountRepository.save(account);
      Account result = accountRepository.findById(account.getAccNo()).get();
      assertEquals(account.getAccNo(), result.getAccNo());	     
   }
   @Test
   public void testFindAll() {
      Account account = getAccount();
      accountRepository.save(account);
      List<Account> result = new ArrayList<>();
      accountRepository.findAll().forEach(e -> result.add(e));
      assertEquals(result.size(), result.size());	     
   }
   @Test
   public void testSave() {
      Account account = getAccount();
      accountRepository.save(account);
      Account found = accountRepository.findById(account.getAccNo()).get();
      assertEquals(account.getAccNo(), found.getAccNo());	     
   }
   @Test
   public void testDeleteById() {
      Account account = getAccount();
      accountRepository.save(account);
      accountRepository.deleteById(account.getAccNo());
      List<Account> result = new ArrayList<>();
      accountRepository.findAll().forEach(e -> result.add(e));
      assertEquals(result.size(), result.size());
   }
   
   private Account getAccount() {
      Account account = new Account();
      account.setAccNo(101);
      account.setAccountType("Saving");
      account.setAcc_balance(2321313);
      return account;
   }
}
