package bank.foundation.avitepa.Avitepa_Bank.Execption;

public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = 1L;
	String Message;
	public InsufficientFundsException(String Message) {
		this.Message=Message;
	}
}
