package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.Account;

import java.util.UUID;

public interface AccountsRepository extends JpaRepository<Account, UUID> {
}
