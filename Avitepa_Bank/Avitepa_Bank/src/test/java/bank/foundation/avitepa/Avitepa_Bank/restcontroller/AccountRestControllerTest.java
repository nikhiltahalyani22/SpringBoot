package bank.foundation.avitepa.Avitepa_Bank.restcontroller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.junit.jupiter.MockitoExtension;
import bank.foundation.avitepa.Avitepa_Bank.entity.Account;
import bank.foundation.avitepa.Avitepa_Bank.entity.CustomerDetail;
import bank.foundation.avitepa.Avitepa_Bank.service.AccountService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers=AccountRestController.class)
class AccountRestControllerTest {
	
	@MockBean
	private AccountService accountServicetest;
		
	@Autowired
	private MockMvc mockmvc;
	
	@Autowired
	ObjectMapper objectMapper;
		
	@InjectMocks
	private AccountRestController accountRestController;
	
	Account Record_1;
	Account Record_2;
	Account Record_3;
	
	
	@BeforeEach
	void setUp()  {
		Record_1 = new Account(101,"Saving",94058, new CustomerDetail(102,"Jim","harpper","New York","aksldhkj@gmail.com",39845695));
		Record_2 = new Account(102,"Saving",9405, new CustomerDetail(103,"Jim","harpper","New York","aksldhkj@gmail.com",39845695));
		Record_3 = new Account(103,"Saving",94033, new CustomerDetail(104,"Jim","harpper","New York","aksldhkj@gmail.com",39845695));	
	}

	@Test
	void testFindAll() throws Exception  {
		List<Account> records = new ArrayList<Account>();
		records.add(Record_1);
		records.add(Record_2);
		records.add(Record_3);
		when(accountServicetest.findAllAccount()).thenReturn(records);
		mockmvc.perform(MockMvcRequestBuilders.get("/api/account").accept(MediaType.ALL).contentType("application/json"))
											.andExpect(MockMvcResultMatchers.status().is(200))
											.andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(records)))
											;
		
	}
	
	@Test
	void testFindByAccNo() throws Exception {
		int id = 101;
		when(accountServicetest.findByAccNo(any(Integer.class))).thenReturn(Record_1);
		mockmvc.perform(MockMvcRequestBuilders.get("/api/account/" + id).accept(MediaType.ALL).contentType("int"))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(Record_1)));
	}
	
	@Test
	void testAccountBalance() throws Exception{
		int id=101;
		when(accountServicetest.accountBalance(any(Integer.class))).thenReturn(Record_1);
		mockmvc.perform(MockMvcRequestBuilders.get("/api/account/accountbalance/"+id).accept(MediaType.ALL).contentType("int"))
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(Record_1.getAcc_balance())));
	}

	@Test
	void testAddAcount() throws Exception {
		when(accountServicetest.addAccount(any(Account.class))).thenReturn(Record_1);
		mockmvc.perform(MockMvcRequestBuilders.post("/api/account/addaccount")
			   .accept(MediaType.ALL).content(objectMapper.writeValueAsString(Record_1))
			   .contentType("application/json"))
			   .andExpect(MockMvcResultMatchers.status().is(201))
			   .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(Record_1)));
	}

	
	@Test
	void testDeleteAcount() throws Exception{
		int id=101;
		String msg = "Account DELELTED";
		when(accountServicetest.deleteAccount(any(Integer.class))).thenReturn(msg);
		mockmvc.perform(MockMvcRequestBuilders.delete("/api/account/delete/" + id).accept(MediaType.ALL))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(msg));
	}

	@Test
	void testTransaction() throws Exception{
		String msg = "Transaction Completed";
		when(accountServicetest.transaction(any(Integer.class) , any(Integer.class), any(Double.class))).thenReturn(msg);
		mockmvc.perform(MockMvcRequestBuilders.put("/api/account/transfer/" +Record_1.getAccNo() + "/" +Record_2.getAccNo()+ "/" +Record_3.getAcc_balance()).accept(MediaType.ALL)
				.content(objectMapper.writeValueAsString(Record_1))
				.contentType("application/json"))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(msg));
	}
	
	@Test
	void testUpdateAccount() throws Exception {
		int id = 101;
		when(accountServicetest.updateAccount(any(Integer.class),any(Account.class))).thenReturn(Record_1);
		mockmvc.perform(MockMvcRequestBuilders.put("/api/account/update/" + id).accept(MediaType.ALL).content(objectMapper.writeValueAsString(Record_1))
				.contentType("application/json"))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(Record_1)));
	}

}
