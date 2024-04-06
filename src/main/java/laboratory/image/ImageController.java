package laboratory.image;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

// 이미지 업로드, 다운로드, 삭제에 대한 HTTP 요청을 처리하는 컨트롤러입니다.
@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    // 이미지를 업로드하는 엔드포인트입니다.
    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = imageService.uploadImage(file);
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다.", e);
        }
    }

    // 이미지를 다운로드하는 엔드포인트입니다.
    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String imageName) {
        try {
            Resource imageResource = imageService.downloadImage(imageName);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageResource);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "이미지를 찾을 수 없습니다.", e);
        }
    }

    // 이미지를 삭제하는 엔드포인트입니다.
    @DeleteMapping("/{imageName}")
    public ResponseEntity<Void> deleteImage(@PathVariable String imageName) {
        try {
            imageService.deleteImage(imageName);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 삭제에 실패했습니다.", e);
        }
    }
}
