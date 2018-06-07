package stockman.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Request implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Long quantity;
	private Date deliveryDate;
	private String activeStatus;
	
//	@OneToMany(mappedBy="request",cascade = CascadeType.ALL)
//	@JsonIgnoreProperties(value = "user")
//	private List<Status> statusList = new ArrayList<Status>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="request_id", referencedColumnName ="id", nullable = true)
	@JsonIgnoreProperties(value = "user")
	private Set<Status> statusList = new HashSet<Status>();
	
	@ManyToOne (cascade= CascadeType.MERGE)
	@JoinColumn(name = "supply_id", nullable = false)
	@JsonIgnoreProperties(value = "requestList")
	private Supply supply;
	
	@ManyToOne (cascade= CascadeType.MERGE)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnoreProperties(value = "requestList")
	private User user;
	
	
}
