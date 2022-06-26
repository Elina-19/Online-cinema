package ru.itis.service;

public interface BlackListService {

    void save(String token);

    boolean exists(String token);

}
