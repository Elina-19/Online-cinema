package ru.itis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RoomExtendedResponse extends RoomResponse {
    private AccountResponse admin;

    private Set<AccountResponse> accounts;
    private FilmResponse currentFilm;
}
