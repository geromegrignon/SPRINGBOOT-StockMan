package stockman.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Provider implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column
	private Long siret;
	private String name;
	
	@OneToOne(fetch = FetchType.EAGER,
            cascade =  CascadeType.ALL,
            mappedBy = "provider")
    private AddressInfo addressInfo;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="provider",cascade = CascadeType.ALL)
	@JsonManagedReference(value="provider-supply")
	private Set<Supply> supplyList = new HashSet<>();
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="provider",cascade = CascadeType.ALL)
	private Set<Contact> contactList = new HashSet<>();
	

}
