package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.FileInfo;

import java.util.Optional;
import java.util.UUID;


public interface FileInfoRepository extends JpaRepository<FileInfo, UUID> {

    Optional<FileInfo> findByStorageFileName(String fileName);

}
