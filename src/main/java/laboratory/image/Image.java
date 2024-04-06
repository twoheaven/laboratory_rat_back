package laboratory.image;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// 이 클래스는 데이터베이스의 이미지(Image) 테이블과 매핑되는 엔티티입니다.
@Entity
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id; // 이미지의 고유 식별자
    
    @Column
    private String name; // 이미지의 원본 파일명
    
    @Column
    private String imageUrl; // 이미지의 저장된 파일명
    
}
