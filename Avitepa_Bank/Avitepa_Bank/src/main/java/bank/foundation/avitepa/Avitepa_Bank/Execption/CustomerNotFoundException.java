package bank.foundation.avitepa.Avitepa_Bank.Execption;

public class CustomerNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	String Message;
	public CustomerNotFoundException(String Message) {
		this.Message=Message;
	}

}
