package personal.project.TripPin;

import jakarta.persistence.GeneratedValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
@Controller
public class MainController {
    @GetMapping("/")
    public String home() {
        return "index"; // 메인 페이지 뷰 파일 (index.html)
    }
}
