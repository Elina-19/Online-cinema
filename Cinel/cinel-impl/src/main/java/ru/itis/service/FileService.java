package ru.itis.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.model.FileInfo;


public interface FileService {

    FileInfo upload(MultipartFile multipart);

}
