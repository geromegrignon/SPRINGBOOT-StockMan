package stockman.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderRequest implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Long quantity;
	private Date deliveryDate;
	
	@ManyToOne (cascade= CascadeType.ALL)
	@JoinColumn(name = "supply_id", nullable = false)
	@JsonBackReference
	private Supply supply;
	
	@ManyToOne (cascade= CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference
	private User user;
	
}
