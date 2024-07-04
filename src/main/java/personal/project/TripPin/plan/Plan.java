package personal.project.TripPin.plan;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private Date startDate;
    private Date endDate;
}
