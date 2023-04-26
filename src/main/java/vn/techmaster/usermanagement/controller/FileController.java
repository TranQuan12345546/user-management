package vn.techmaster.usermanagement.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.usermanagement.model.FileResponse;
import vn.techmaster.usermanagement.service.FileService;

@RestController
public class FileController {
    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("files")
    public ResponseEntity<?> uploadFile(@ModelAttribute("file") MultipartFile file) {
        FileResponse fileResponse = fileService.uploadFile(file);
        return ResponseEntity.ok(fileResponse);
    }

    // 2. Xem thông tin file
    @GetMapping(value = "files/{id}")
    public ResponseEntity<?> readFile(@PathVariable String id) {
        byte[] bytes = fileService.readFile(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
