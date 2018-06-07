package stockman.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Status implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	private Date updatedAt;
	private String comment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference(value = "user-status")
	private User user;
	
//	@ManyToOne
//	@JoinColumn(name = "request_id")
//	@JsonIgnoreProperties(value = "statusList")
//	private Request request;
	
	@ManyToOne
	@JoinColumn(name = "request_id")
	@JsonIgnoreProperties(value = "statusList")
	private Request request;
	
}
