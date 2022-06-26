package ru.itis.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import ru.itis.model.FileInfo;


public interface FileService {

    FileInfo upload(MultipartFile multipart);

    ResponseEntity<byte[]> getFile(String fileName);

}
