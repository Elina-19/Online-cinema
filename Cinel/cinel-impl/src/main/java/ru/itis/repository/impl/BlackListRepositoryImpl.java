package ru.itis.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.itis.dto.TokensDto;
import ru.itis.repository.BlackListRepository;

import java.util.UUID;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@RequiredArgsConstructor
@Repository
public class BlackListRepositoryImpl implements BlackListRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public void save(String token) {
        mongoTemplate.save(TokensDto.builder()
                ._id(UUID.randomUUID().toString())
                .accessToken(token)
                .build(), "tokens");
    }

    @Override
    public boolean exists(String token) {
        return mongoTemplate.exists(
                Query.query(where("accessToken").is(token)), "tokens");
    }
}
