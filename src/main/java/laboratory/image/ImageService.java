package laboratory.image;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// 이미지 업로드, 다운로드, 삭제에 대한 비즈니스 로직을 처리하는 서비스 클래스입니다.
@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private AmazonS3Service amazonS3Service;

    // 이미지를 업로드하는 메서드입니다.
    public String uploadImage(MultipartFile file) throws IOException {
        return amazonS3Service.uploadImage(file);
    }

    // 이미지를 다운로드하는 메서드입니다.
    public Resource downloadImage(String imageName) throws IOException {
        return amazonS3Service.downloadImage(imageName);
    }

    // 이미지를 삭제하는 메서드입니다.
    public void deleteImage(String imageName) {
        amazonS3Service.deleteImage(imageName);
    }
}
