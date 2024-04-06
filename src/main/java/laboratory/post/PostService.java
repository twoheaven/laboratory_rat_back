package laboratory.post;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 이 클래스는 게시물(Post)에 대한 비즈니스 로직을 처리하는 서비스 클래스입니다.
@Service
public class PostService {
	
	private final PostRepository postRepository;

	@Autowired
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	// 모든 게시물 목록을 가져오는 메서드입니다.
	public List<Post> getAllPosts(){
		return postRepository.findAll();
	}
	
	// 특정 ID를 가진 게시물을 가져오는 메서드입니다.
	public Optional<Post> getPostById(Long id){
		return postRepository.findById(id);
	}
	
	// 새로운 게시물을 생성하는 메서드입니다.
	public Post createPost(Post post) {
		return postRepository.save(post);
	}
	
	// 기존 게시물을 업데이트하는 메서드입니다.
	public Post updatePost(Long id, Post updatedPost) {
		Optional<Post> optionalPost = postRepository.findById(id);
		if(optionalPost.isPresent()) {
			Post post = optionalPost.get();
			post.setTitle(updatedPost.getTitle());
			post.setContent(updatedPost.getContent());
			return postRepository.save(post);
		} else {
			throw new IllegalArgumentException("ID가 " + id + "인 게시물을 찾을 수 없습니다.");
		}
	}
	
	// 특정 ID를 가진 게시물을 삭제하는 메서드입니다.
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}

}
