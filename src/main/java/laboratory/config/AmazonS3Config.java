package laboratory.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

// Amazon S3 클라이언트를 구성하는 클래스입니다.
@Configuration
public class AmazonS3Config {

    @Value("${aws.region}")
    private String region; // AWS 리전

    @Value("${aws.accessKey}")
    private String accessKey; // AWS 액세스 키

    @Value("${aws.secretKey}")
    private String secretKey; // AWS 시크릿 키

    // Amazon S3 클라이언트 빈을 생성합니다.
    @Bean
    public AmazonS3 s3Client() {
        // AWS 액세스 키와 시크릿 키로 인증 정보를 생성합니다.
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        // AmazonS3ClientBuilder를 사용하여 Amazon S3 클라이언트를 빌드합니다.
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)) // 생성한 인증 정보를 사용하여 S3 클라이언트를 설정합니다.
                .withRegion(region) // AWS 리전을 설정합니다.
                .build();

        // AWS S3 클라이언트 연결을 확인합니다.
        try {
            amazonS3.listBuckets(); // S3 서비스에 연결하여 버킷 목록을 가져옵니다.
        } catch (Exception e) {
            // AWS 서비스에 연결할 수 없을 때 예외 처리
            throw new RuntimeException("AWS S3 서비스에 연결할 수 없습니다.", e);
        }

        return amazonS3; // 구성된 Amazon S3 클라이언트를 반환합니다.
    }
}
