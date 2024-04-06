package laboratory.post;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// 이 클래스는 데이터베이스의 게시물(Post) 테이블과 매핑되는 엔티티입니다.
@Entity
@Data
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id; // 게시물의 고유 식별자
	
	@Column
	private String title; // 게시물의 제목
	
	@Column(columnDefinition = "TEXT")
	private Long content; // 게시물의 내용, 긴 텍스트로 정의됩니다.
	
	@Column
	private Date createdDate; // 게시물이 생성된 날짜
}
