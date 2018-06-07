package stockman.model;

import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Status{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	private Date updatedAt;
	private String comment;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties("requestList")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "request_id")
	@JsonIgnoreProperties("statusList")
	private Request request;
	
}
