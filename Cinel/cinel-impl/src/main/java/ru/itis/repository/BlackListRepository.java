package ru.itis.repository;

public interface BlackListRepository {

    void save(String token);

    boolean exists(String token);

}
