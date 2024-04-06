package laboratory.image;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    // 추가적인 이미지 관련 메서드가 필요한 경우 여기에 작성합니다.
}
