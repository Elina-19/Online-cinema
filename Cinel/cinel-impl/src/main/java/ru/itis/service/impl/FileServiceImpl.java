package ru.itis.service.impl;

import liquibase.util.file.FilenameUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.exception.UploadFailException;
import org.springframework.http.ResponseEntity;
import ru.itis.exception.FileNotFoundException;
import ru.itis.model.FileInfo;
import ru.itis.repository.FileInfoRepository;
import ru.itis.service.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final FileInfoRepository fileInfoRepository;

    @Value("${files.storage.path}")
    private String storagePath;

    @Override
    public FileInfo upload(MultipartFile multipart) {
        try {
            String extension = FilenameUtils.getExtension(multipart.getOriginalFilename());

            String storageFileName = UUID.randomUUID() + "." + extension;

            FileInfo file = FileInfo.builder()
                    .mimeType(multipart.getContentType())
                    .originalFileName(multipart.getOriginalFilename())
                    .storageFileName(storageFileName)
                    .size(multipart.getSize())
                    .build();

            Files.copy(multipart.getInputStream(), Paths.get(storagePath, file.getStorageFileName()));

            return fileInfoRepository.save(file);
        } catch (IOException ex) {
            throw new UploadFailException("Cant upload file " + multipart.getOriginalFilename());
        }
    }

    public ResponseEntity<byte[]> getFile(String fileName) {
        FileInfo fileInfo = fileInfoRepository
                .findByStorageFileName(fileName)
                .orElseThrow(FileNotFoundException::new);

        try{
            byte[] file =  Files.readAllBytes(
                    Paths.get(storagePath, fileName));

            return ResponseEntity.ok()
                    .header("Content-Type", fileInfo.getMimeType())
                    .header("Content-Length", fileInfo.getSize().toString())
                    .body(file);
        }catch (IOException e){
            throw new FileNotFoundException();
        }
    }
}
