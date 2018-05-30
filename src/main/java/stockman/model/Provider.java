package stockman.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "provider")
    private AddressInfo addressInfo;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="provider",cascade = CascadeType.ALL)
	private List<Supply> supplyList = new ArrayList<>();
	

}
