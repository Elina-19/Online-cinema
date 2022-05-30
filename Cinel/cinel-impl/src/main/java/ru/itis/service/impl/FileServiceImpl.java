package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.itis.exception.FileNotFoundException;
import ru.itis.model.FileInfo;
import ru.itis.repository.FileInfoRepository;
import ru.itis.service.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final FileInfoRepository fileInfoRepository;

    @Value("${files.storage.path}")
    private String storagePath;

    @Override
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
