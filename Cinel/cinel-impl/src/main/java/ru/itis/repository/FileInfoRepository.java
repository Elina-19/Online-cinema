package ru.itis.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itis.model.FileInfo;

import java.util.UUID;

public interface FileInfoRepository extends CrudRepository<FileInfo, UUID> {
}
