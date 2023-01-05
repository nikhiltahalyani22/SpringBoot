package bank.foundation.avitepa.Avitepa_Bank.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@NoArgsConstructor 
@AllArgsConstructor
@Entity
@Table(name="customer_detail")
public class CustomerDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customer_id;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private int mobileNumber;
	
	@OneToOne(mappedBy="customerDetail" ,cascade=CascadeType.ALL)
	@JsonIgnoreProperties("customerDetail")
	private Account account;

	
	
	public CustomerDetail(int customer_id, String firstName, String lastName, String address, String email,
			int mobileNumber) {
		super();
		this.customer_id = customer_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}
	
	
	

	
}
 