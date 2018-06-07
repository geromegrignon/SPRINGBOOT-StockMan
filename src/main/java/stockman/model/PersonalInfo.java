package stockman.model;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class PersonalInfo {
	
	private String firstname;
	private String lastname;
	private String email;
	private String phoneNumber;
	

}
