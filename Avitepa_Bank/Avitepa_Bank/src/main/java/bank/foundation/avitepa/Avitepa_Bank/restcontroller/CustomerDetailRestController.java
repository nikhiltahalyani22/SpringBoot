package bank.foundation.avitepa.Avitepa_Bank.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bank.foundation.avitepa.Avitepa_Bank.Execption.CustomerNotFoundException;
import bank.foundation.avitepa.Avitepa_Bank.Execption.NoDataException;
import bank.foundation.avitepa.Avitepa_Bank.entity.CustomerDetail;
import bank.foundation.avitepa.Avitepa_Bank.service.CustomerDetailService;

@RestController
@RequestMapping("/api")
public class CustomerDetailRestController {
	

	private CustomerDetailService customerDetailService;
	

	@Autowired
	public CustomerDetailRestController(CustomerDetailService thecustomerDetailService) {
		customerDetailService = thecustomerDetailService;
	}
	
	@GetMapping("/customer")
	public ResponseEntity <?> findAllCustomer() throws NoDataException{
		List<CustomerDetail> customer= customerDetailService.findAllCustomer();
		if(!customer.isEmpty()) {
			return new ResponseEntity <>(customer,HttpStatus.OK);
		}
		else {
			throw new NoDataException("No Customer Found");
	  }
	}
	
	@GetMapping("/customer/{theCusNo}")
	public ResponseEntity <?> findByCusNo(@PathVariable int theCusNo) throws CustomerNotFoundException{
		CustomerDetail customer = customerDetailService.findByCusNo(theCusNo);
		if(customer !=null) {
			return new ResponseEntity <>(customer,HttpStatus.OK);
		}
		else {		
			throw new CustomerNotFoundException("Account not found with account number");
		}
	}
	
	@PutMapping("/customer/update/{theCusNo}")
	public ResponseEntity<?> updateCustomer(@PathVariable int theCusNo, @RequestBody CustomerDetail customerRe ) throws CustomerNotFoundException {
		CustomerDetail customerDetail =customerDetailService.updateCustomer(theCusNo, customerRe);
		if (customerDetail != null) {
			return new ResponseEntity <> (customerDetail,HttpStatus.OK);
		}
		else {
			throw new CustomerNotFoundException("Account not found with account number");
		}
	}


}
