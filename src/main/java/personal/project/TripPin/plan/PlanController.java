package personal.project.TripPin.plan;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String viewPlan(Model model) {
        // 필요한 데이터를 모델에 추가하고
        List<Plan> plans = planService.getAllPlans();
        model.addAttribute("plan", plans);

        // 뷰 이름을 반환합니다.
        return "plan"; // plan_list.html과 매핑된 경로
    }


    @GetMapping("/list")
    public String viewPlanList(Model model) {
        // 필요한 데이터를 모델에 추가하고
        List<Plan> plans = planService.getAllPlans();
        model.addAttribute("plans", plans);

        // 뷰 이름을 반환합니다.
        return "plan_list"; // plan_list.html과 매핑된 경로
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
            plan.setLatitude(planDetails.getLatitude()); // 추가된 필드
            plan.setLongitude(planDetails.getLongitude()); // 추가된 필드
            return planService.savePlan(plan);
        } else {
            throw new RuntimeException("Plan not found with id " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Plan> getAllPlans(@RequestParam(required = false, defaultValue = "latest") String sort) {
        return planService.getAllPlans(sort);
    }


    @GetMapping("/plan/{id}/map")
    @ResponseBody
    public MapData getPlanMapDetails(@PathVariable Long id) {
        Optional<Plan> optionalPlan = planService.getPlanById(id);
        if (optionalPlan.isPresent()) {
            Plan plan = optionalPlan.get();
            // 여기서는 Plan 객체에서 latitude와 longitude를 가져와 MapData 객체에 담아 반환합니다.
            MapData mapData = new MapData(plan.getLatitude(), plan.getLongitude());
            return mapData;
        } else {
            throw new RuntimeException("Plan not found with id " + id);
        }
    }

    // MapData 클래스는 Plan의 지도 정보를 담을 DTO 클래스로 가정합니다.
    static class MapData {
        private double latitude;
        private double longitude;

        public MapData(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        // Getter, Setter 생략
    }
}
