package laboratory.image;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

// Amazon S3와 상호작용하여 이미지 업로드, 다운로드, 삭제를 처리하는 서비스 클래스입니다.
@Service
public class AmazonS3Service {

    @Autowired
    private AmazonS3 amazonS3; // Amazon S3 클라이언트

    @Value("${aws.s3.bucketName}")
    private String bucketName; // S3 버킷 이름

    // 이미지를 업로드하고 이미지 URL을 반환합니다.
    public String uploadImage(MultipartFile file) throws IOException {
        // 고유한 파일명 생성
        String fileName = generateUniqueFileName(file.getOriginalFilename());
        // 이미지를 S3에 업로드합니다.
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), new ObjectMetadata())
                .withCannedAcl(CannedAccessControlList.PublicRead));
        // 업로드된 이미지의 URL을 반환합니다.
        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    // 이미지를 다운로드하여 리소스로 반환합니다.
    public Resource downloadImage(String imageName) throws IOException {
        // S3에서 이미지 객체를 가져옵니다.
        S3Object s3Object = amazonS3.getObject(bucketName, imageName);
        // 이미지 객체의 입력 스트림을 가져옵니다.
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        // 입력 스트림에서 바이트 배열로 이미지 데이터를 읽어옵니다.
        byte[] bytes = inputStream.readAllBytes();
        // 바이트 배열로부터 ByteArrayResource를 생성하여 반환합니다.
        return new ByteArrayResource(bytes);
    }

    // 이미지를 삭제합니다.
    public void deleteImage(String imageName) {
        // S3에서 이미지를 삭제합니다.
        amazonS3.deleteObject(bucketName, imageName);
    }

    // 고유한 파일명을 생성합니다.
    private String generateUniqueFileName(String originalFileName) {
        // UUID를 사용하여 고유한 파일명을 생성합니다.
        return UUID.randomUUID().toString() + "_" + originalFileName;
    }
}
