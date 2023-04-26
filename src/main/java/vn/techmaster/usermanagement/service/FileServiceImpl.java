package vn.techmaster.usermanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.usermanagement.exception.FileHandleExeption;
import vn.techmaster.usermanagement.exception.FileRequestException;
import vn.techmaster.usermanagement.exception.NotFoundException;
import vn.techmaster.usermanagement.model.FileResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
    private final Path rootPath;
    public FileServiceImpl(@Value("${root-path}") String rootPathString) {
        rootPath= Paths.get(rootPathString);
        createFolder(rootPath);
    }

    private void createFolder(Path path) {
        if (Files.notExists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                log.error("Error when creating directory", e);
                throw new FileHandleExeption("Could not create directory to upload");
            }
        }
    }
    @Override
    public FileResponse uploadFile(MultipartFile file) {
        validateFile(file);

        String fileId = UUID.randomUUID().toString();
        Path filePath = rootPath.resolve(fileId);
        File fileUpload = new File(filePath.toString());

        try (OutputStream outStream = new FileOutputStream(fileUpload)) {
            outStream.write(file.getBytes());
            return new FileResponse("/api/v1/files/" + fileId);
        } catch (IOException e) {
            throw new FileHandleExeption("Error occurs when uploading file");
        }
    }
    private void validateFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        // File name is not empty
        if(fileName == null || fileName.isEmpty()) {
            throw new FileRequestException("File name is not valid");
        }

        // File type in specific list
        // avatar.png, image.jpg => png, jpg
        String fileExtension = getFileExtension(fileName);
        if(!checkFileExtension(fileExtension)) {
            throw new FileRequestException("File type is not valid");
        }

        // File size <= 2MB
        double fileSize = (double) (file.getSize() / 1_048_576);
        if(fileSize > 2) {
            throw new FileRequestException("File size is over 2MB");
        }
    }

    public String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        if(lastIndex == -1) {
            return "";
        }
        return fileName.substring(lastIndex + 1);
    }

    public boolean checkFileExtension(String fileExtension) {
        List<String> fileExtensions = List.of("png", "jpg", "jpeg");
        return fileExtensions.contains(fileExtension);
    }
    @Override
    public byte[] readFile(String fileName) {
        Path filePath = rootPath.resolve(fileName);
        if (Files.notExists(rootPath.resolve(fileName))) {
            throw new NotFoundException("Not found file name = " + fileName);
        }
        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new FileHandleExeption("Error when reading file");
        }
    }

    @Override
    public void deleteFile(String fileId) {
        Path filePath = rootPath.resolve(fileId);
        if (Files.notExists(rootPath.resolve(fileId))) {
            throw new NotFoundException("Not found file name = " + fileId);
        }
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new FileHandleExeption("Error when deleting file");
        }
    }
}
