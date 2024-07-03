package personal.project.TripPin.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.project.TripPin.util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final FileUtil fileUtil;

    @Autowired
    public ImageService(ImageRepository imageRepository, FileUtil fileUtil) {
        this.imageRepository = imageRepository;
        this.fileUtil = fileUtil;
    }

    public List<Image> getDefaultImages() {
        //Image img = new Image();
        List<Image> imageList = new ArrayList<>();

        List<File> fileList = fileUtil.getFileList("C:\\Users\\admin\\Documents\\TripPin\\src\\main\\resources\\static\\images\\main");
        for(File file : fileList) {
            String filename = file.getName();
            String alt = filename.split("\\.")[0];
            String url = "/images/main/" + filename;

            Image img = new Image();
            img.setImageUrl(url);
            img.setAlt(alt);
            imageList.add(img);

        }
        return imageList;
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }
}
