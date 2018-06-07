package stockman.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AddressInfo implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String streetNumber; // string type to allow char annotations, ex : '1bis'
	private String streetName;
	private Long postalCode;
	private String city;
	private String country;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
	@JsonBackReference(value = "provider-addressInfo")
    private Provider provider;
	

}
