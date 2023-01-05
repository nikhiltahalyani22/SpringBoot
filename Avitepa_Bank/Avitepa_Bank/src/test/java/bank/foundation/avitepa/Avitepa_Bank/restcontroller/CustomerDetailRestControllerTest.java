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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

import bank.foundation.avitepa.Avitepa_Bank.entity.CustomerDetail;
import bank.foundation.avitepa.Avitepa_Bank.service.CustomerDetailService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers=CustomerDetailRestController.class)
class CustomerDetailRestControllerTest {
	
	@MockBean
	private CustomerDetailService customerDetailServicetest;
		
	@Autowired
	private MockMvc mockmvc;
	
	@Autowired
	ObjectMapper objectMapper;
		
	@InjectMocks
	private CustomerDetailRestController customerDetailRestController;
	
	CustomerDetail Record_1;
	CustomerDetail Record_2;
	CustomerDetail Record_3;
	
	@BeforeEach
	void setUp() throws Exception {
		Record_1 = new CustomerDetail(102,"Jim","harpper","New York","aksldhkj@gmail.com",39845695);
		Record_2 = new CustomerDetail(103,"Jim","harpper","New York","aksldhkj@gmail.com",39845695);
		Record_3 = new CustomerDetail(104,"Jim","harpper","New York","aksldhkj@gmail.com",39845695);
	}



	@Test
	void testFindAllCustomer() throws Exception {
		List<CustomerDetail> records = new ArrayList<CustomerDetail>();
		records.add(Record_1);
		records.add(Record_2);
		records.add(Record_3);
		when(customerDetailServicetest.findAllCustomer()).thenReturn(records);
		mockmvc.perform(MockMvcRequestBuilders.get("/api/customer").accept(MediaType.ALL).contentType("application/json"))
											.andExpect(MockMvcResultMatchers.status().is(200))
											.andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(records)));
	}

	@Test
	void testFindByCusNo() throws Exception{
		int id = 101;
		when(customerDetailServicetest.findByCusNo(any(Integer.class))).thenReturn(Record_1);
		mockmvc.perform(MockMvcRequestBuilders.get("/api/customer/" + id).accept(MediaType.ALL).contentType("int"))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(Record_1)));
	}

	@Test
	void testUpdateCustomer() throws Exception {
		int id = 101;
		when(customerDetailServicetest.updateCustomer(any(Integer.class),any(CustomerDetail.class))).thenReturn(Record_1);
		mockmvc.perform(MockMvcRequestBuilders.put("/api/customer/update/" + id).accept(MediaType.ALL).content(objectMapper.writeValueAsString(Record_1))
				.contentType("application/json"))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(Record_1)));
	}
	

}
