package bank.foundation.avitepa.Avitepa_Bank.Execption;

public class NoDataException extends Exception {

	private static final long serialVersionUID = 1L;
	String Message;
	public NoDataException(String Message) {
		this.Message=Message;
	}

}
