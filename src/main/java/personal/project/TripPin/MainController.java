package personal.project.TripPin;

import jakarta.persistence.GeneratedValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import personal.project.TripPin.image.Image;
import personal.project.TripPin.image.ImageService;

import java.security.Principal;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class MainController {
    private final ImageService imageService;

    @GetMapping("/")
    public String home(Model model) {
        List<Image> images = imageService.getDefaultImages();
        model.addAttribute("images", images);

        return "index"; // 메인 페이지 뷰 파일 (index.html)
    }
}
