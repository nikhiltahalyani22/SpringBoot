package bank.foundation.avitepa.Avitepa_Bank.Execption;

public class AccountNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	String Message;
	public AccountNotFoundException(String Message) {
		this.Message=Message;
	}
}
