package stockman.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
import stockman.model.audit.DateAudit;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Request extends DateAudit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Long quantity;
	private Date deliveryDate;
	private String activeStatus;

	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="request_id", referencedColumnName ="id", nullable = true)
	private List<Status> statusList = new ArrayList<Status>();
	
	@ManyToOne
	@JoinColumn(name = "supply_id")
	@JsonIgnoreProperties("requestList")
	private Supply supply;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties("requestList")
	private User user;
	
	
}
