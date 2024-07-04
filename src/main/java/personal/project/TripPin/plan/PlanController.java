package personal.project.TripPin.plan;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import personal.project.TripPin.member.MemberService;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    private PlanService planService;

    @GetMapping
    public List<Plan> getAllPlans() {
        return planService.getAllPlans();
    }

    @GetMapping("/{id}")
    public Optional<Plan> getPlanById(@PathVariable Long id) {
        return planService.getPlanById(id);
    }

    @PostMapping
    public Plan createPlan(@RequestBody Plan plan) {
        return planService.savePlan(plan);
    }

    @PutMapping("/{id}")
    public Plan updatePlan(@PathVariable Long id, @RequestBody Plan planDetails) {
        Optional<Plan> planOptional = planService.getPlanById(id);
        if (planOptional.isPresent()) {
            Plan plan = planOptional.get();
            plan.setLocation(planDetails.getLocation());
            plan.setStartDate(planDetails.getStartDate());
            plan.setEndDate(planDetails.getEndDate());
            return planService.savePlan(plan);
        } else {
            throw new RuntimeException("Plan not found with id " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
    }
}
