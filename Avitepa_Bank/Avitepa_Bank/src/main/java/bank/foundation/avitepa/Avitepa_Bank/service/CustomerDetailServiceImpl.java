package bank.foundation.avitepa.Avitepa_Bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.foundation.avitepa.Avitepa_Bank.entity.CustomerDetail;
import bank.foundation.avitepa.Avitepa_Bank.repository.CustomerDetailRepository;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {
	
	private CustomerDetailRepository customerDetailRepository;
	
	@Autowired
	public CustomerDetailServiceImpl(CustomerDetailRepository theCustomerDetailRepository) {
		customerDetailRepository=theCustomerDetailRepository;
	}
	
	@Override
	public List<CustomerDetail> findAllCustomer() {
		return customerDetailRepository.findAll();
	
	}

	@Override
	public CustomerDetail findByCusNo(int theCusNo) {
		Optional<CustomerDetail> customer = customerDetailRepository.findById(theCusNo);
		CustomerDetail theCustomerDetail = null;
		if (customer.isPresent()) {
			theCustomerDetail = customer.get();
		}
		else {
			return null;
		}
		return theCustomerDetail;
	}

	@Override
	public CustomerDetail updateCustomer(int theCusNo, CustomerDetail customerRe) {
		CustomerDetail theCustomerDetail;
		Optional<CustomerDetail> customer = customerDetailRepository.findById(theCusNo);
		if (customer.isPresent()) {
			theCustomerDetail=customer.get();
			theCustomerDetail.setFirstName(customerRe.getFirstName());
			theCustomerDetail.setLastName(customerRe.getLastName());
			theCustomerDetail.setAddress(customerRe.getAddress());
			theCustomerDetail.setEmail(customerRe.getEmail());
			theCustomerDetail.setMobileNumber(customerRe.getMobileNumber());
			return customerDetailRepository.save(theCustomerDetail);
		}
		else {
			return null;
		}
	}
}
