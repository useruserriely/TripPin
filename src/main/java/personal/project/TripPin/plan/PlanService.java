package personal.project.TripPin.plan;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanService {
    @Autowired
    private PlanRepository planRepository;

    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }

    public List<Plan> getAllPlans(String sort) {
        if ("oldest".equals(sort)) {
            return planRepository.findAllByOrderByStartDateAsc();
        } else {
            return planRepository.findAllByOrderByStartDateDesc();
        }
    }

    public Optional<Plan> getPlanById(Long id) {
        return planRepository.findById(id);
    }

    public Plan savePlan(Plan plan) {
        return planRepository.save(plan);
    }

    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }
}
