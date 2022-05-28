package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.AccountRefreshTokenEntity;

import java.util.List;
import java.util.UUID;

public interface AccountRefreshTokenRepository extends JpaRepository<AccountRefreshTokenEntity, UUID> {

    List<AccountRefreshTokenEntity> findAllByAccountId(UUID accountId);

}
