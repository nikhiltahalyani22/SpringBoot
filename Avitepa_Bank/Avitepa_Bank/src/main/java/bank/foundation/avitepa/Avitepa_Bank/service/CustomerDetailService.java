package bank.foundation.avitepa.Avitepa_Bank.service;

import java.util.List;

import bank.foundation.avitepa.Avitepa_Bank.entity.CustomerDetail;


public interface CustomerDetailService {
	
	
	public List<CustomerDetail> findAllCustomer();
	
	public CustomerDetail findByCusNo(int theCusNo);
	
	public CustomerDetail updateCustomer(int theCusNo, CustomerDetail customerResponse);

}
