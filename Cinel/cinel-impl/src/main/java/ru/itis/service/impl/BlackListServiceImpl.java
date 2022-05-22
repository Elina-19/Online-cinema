package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.repository.BlackListRepository;
import ru.itis.service.BlackListService;

@RequiredArgsConstructor
@Service
public class BlackListServiceImpl implements BlackListService {

    private final BlackListRepository blackListRepository;

    @Override
    public void save(String token) {
        blackListRepository.save(token);
    }

    @Override
    public boolean exists(String token) {
        return blackListRepository.exists(token);
    }

}
