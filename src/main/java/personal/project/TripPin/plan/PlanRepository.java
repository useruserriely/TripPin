package personal.project.TripPin.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findAllByOrderByStartDateDesc();
    List<Plan> findAllByOrderByStartDateAsc();
}
