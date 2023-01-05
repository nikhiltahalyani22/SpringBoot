package bank.foundation.avitepa.Avitepa_Bank.Execption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExecptionHandler {
	
	@ExceptionHandler(value = AccountNotFoundException.class)
	public ResponseEntity<?> handler(AccountNotFoundException exp){
		return new ResponseEntity<>("Account not found with given account number",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<?> handler(CustomerNotFoundException exp){
		return new ResponseEntity<>("Customer not found with given account number",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = InsufficientFundsException.class)
	public ResponseEntity<?> handler(InsufficientFundsException exp){
		return new ResponseEntity<>("Insufficient funds to transfer",HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(value = NoDataException.class)
	public ResponseEntity<?> handler(NoDataException exp){
		return new ResponseEntity<>("No Data Found",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<?> handler(HttpMessageNotReadableException exp){
		return new ResponseEntity<>("Please enter a vaild input ",HttpStatus.BAD_REQUEST);
	}
	

}
