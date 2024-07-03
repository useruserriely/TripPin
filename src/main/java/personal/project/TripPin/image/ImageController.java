package personal.project.TripPin.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

//    @GetMapping("/images")
//    public String showImages(Model model) {
//        //List<Image> images = imageService.getAllImages();
//        List<Image> images = imageService.getDefaultImages();
//        model.addAttribute("images", images);
//        return "image-list"; // Thymeleaf 템플릿 이름 반환
//    }
}
