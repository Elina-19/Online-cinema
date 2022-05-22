package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import ru.itis.dto.request.SignUpRequest;
import ru.itis.dto.response.AccountResponse;
import ru.itis.model.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountResponse toResponse(Account account);

}
