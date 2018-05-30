package stockman.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.MappedSuperclass;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@JsonIgnoreProperties(
        value = {"createdBy", "updatedBy"},
        allowGetters = true
)
public abstract class UserDateAudit extends DateAudit {

	@CreatedBy
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;
}
