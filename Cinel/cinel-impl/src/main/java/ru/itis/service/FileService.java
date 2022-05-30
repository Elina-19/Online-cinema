package ru.itis.service;

import org.springframework.http.ResponseEntity;

public interface FileService {

    ResponseEntity<byte[]> getFile(String fileName);

}
