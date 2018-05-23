package stockman.modele;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Supply implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column
	private String name;
	
	@Column
	private String description;
	private Long unitsInStock;
	private Long alertStock;
	
	@ManyToOne (cascade= CascadeType.ALL)
	@JoinColumn(name = "provider_id", nullable = false)
	@JsonBackReference
	private Provider provider;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="supply",cascade = CascadeType.ALL)
	private List<OrderRequest> orderRequestList = new ArrayList<>();
	
	
	
	
}
