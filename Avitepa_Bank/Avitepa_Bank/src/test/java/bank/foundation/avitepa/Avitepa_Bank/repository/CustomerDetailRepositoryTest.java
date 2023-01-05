package bank.foundation.avitepa.Avitepa_Bank.repository;

import static org.junit.jupiter.api.Assertions.*;

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
import bank.foundation.avitepa.Avitepa_Bank.entity.CustomerDetail;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = AvitepaBankApplication.class)
class CustomerDetailRepositoryTest {

	
	@MockBean
	CustomerDetail customer ;
	
    @Autowired
    CustomerDetailRepository customerRepository;
   
   @BeforeEach
	void setUp()  {

	    customer = getCustomerDetail();
		
	}
   
   @Test
   public void testFindById() {
      CustomerDetail customer = getCustomerDetail();	     
      customerRepository.save(customer);
      CustomerDetail result = customerRepository.findById(customer.getCustomer_id()).get();
      assertEquals(customer.getCustomer_id(), result.getCustomer_id());	     
   }
   @Test
   public void testFindAll() {
      CustomerDetail customer = getCustomerDetail();
      customerRepository.save(customer);
      List<CustomerDetail> result = new ArrayList<>();
      customerRepository.findAll().forEach(e -> result.add(e));
      assertEquals(result.size(), result.size());	     
   }
   @Test
   public void testSave() {
      CustomerDetail customer = getCustomerDetail();
      customerRepository.save(customer);
      CustomerDetail found = customerRepository.findById(customer.getCustomer_id()).get();
      assertEquals(customer.getCustomer_id(), found.getCustomer_id());	     
   }

   
   private CustomerDetail getCustomerDetail() {
      CustomerDetail customer = new CustomerDetail();
      customer.setCustomer_id(101);
      customer.setFirstName("Jim");
      customer.setLastName("Harper");
      customer.setAddress("Scranton");
      customer.setEmail("jharper@gmail.com");
      customer.setMobileNumber(82439420);
      return customer;
   }
}
