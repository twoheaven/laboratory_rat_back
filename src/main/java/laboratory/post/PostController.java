package laboratory.post;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 이 클래스는 게시물(Post)에 대한 HTTP 요청을 처리하는 컨트롤러입니다.
@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	private final PostService postService;
	
	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;	
	}
	
	// 모든 게시물 목록을 가져오는 엔드포인트입니다.
	@GetMapping
	public List<Post> getAllPosts(){
		return postService.getAllPosts();
	}
	
	// 특정 ID를 가진 게시물을 가져오는 엔드포인트입니다.
	@GetMapping("/{id}")
	public Optional<Post> getPostById(@PathVariable Long id){
		return postService.getPostById(id);
	}
	
	// 새로운 게시물을 생성하는 엔드포인트입니다.
	@PostMapping
	public Post createPost(@RequestBody Post post) {
		return postService.createPost(post);
	}
	
	// 기존 게시물을 업데이트하는 엔드포인트입니다.
	@PutMapping("/{id}")
	public Post updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
		return postService.updatePost(id, updatedPost);
	}
	
	// 특정 ID를 가진 게시물을 삭제하는 엔드포인트입니다.
	@DeleteMapping("/{id}")
	public void deletePost(@PathVariable Long id) {
		postService.deletePost(id);
	}

}
