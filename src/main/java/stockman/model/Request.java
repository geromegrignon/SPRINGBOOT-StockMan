package stockman.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Long quantity;
	private Date deliveryDate;
	private String activeStatus;

	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="request_id", referencedColumnName ="id", nullable = true)
	private Set<Status> statusList = new HashSet<Status>();
	
	@ManyToOne
	@JoinColumn(name = "supply_id")
	@JsonIgnoreProperties("requestList")
	private Supply supply;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties("requestList")
	private User user;
	
	
}
