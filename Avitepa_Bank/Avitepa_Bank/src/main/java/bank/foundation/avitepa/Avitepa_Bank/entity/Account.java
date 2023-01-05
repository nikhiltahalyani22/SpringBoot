package bank.foundation.avitepa.Avitepa_Bank.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



//annotate the class as an entity and map to the database
@Entity
@Table(name="account")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	//define the field and annotate the fields with db colum names
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int accNo;	
	private String accountType;	
	private double acc_balance;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="customer_detail_id")
	@JsonIgnoreProperties("account")
	private CustomerDetail customerDetail;

	public Account(String accountType, int acc_balance, CustomerDetail customerDetail) {
		this.accountType = accountType;
		this.acc_balance = acc_balance;
		this.customerDetail = customerDetail;
	}


	
	
	
	
}
