package personal.project.TripPin.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FileUtil {
    public List<File> getFileList(String path) {
        // 파일 목록을 가져올 디렉토리 경로

        // 해당 경로의 디렉토리를 가리키는 File 객체 생성
        File directory = new File(path);

        // 해당 경로가 디렉토리인지 확인
        if (directory.isDirectory()) {
            // 디렉토리 내의 파일 목록을 가져오기
            File[] files = directory.listFiles();

            if (files != null) {
                return Arrays.asList(files);

            } else {
                System.out.println("디렉토리 내 파일이 없습니다.");
            }
        } else {
            System.out.println("해당 경로는 디렉토리가 아닙니다.");
        }

        return new ArrayList<File>();
    }
}
