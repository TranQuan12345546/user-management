package vn.techmaster.usermanagement.service;

import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.usermanagement.model.FileResponse;

public interface FileService {
    FileResponse uploadFile(MultipartFile file);
    byte[] readFile(String fileName);
    void deleteFile(String id);
}
