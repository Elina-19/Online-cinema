package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.request.RoomRequest;
import ru.itis.dto.response.RoomExtendedResponse;
import ru.itis.dto.response.RoomResponse;
import ru.itis.exception.RoomNotExistException;
import ru.itis.exception.RoomNotFoundException;
import ru.itis.model.Account;
import ru.itis.model.Film;
import ru.itis.model.Room;
import ru.itis.repository.RoomRepository;
import ru.itis.service.AccountService;
import ru.itis.service.FilmService;
import ru.itis.service.RoomService;
import ru.itis.utils.mapper.RoomMapper;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {

    private final static int CODE_LENGTH = 10;

    private final RoomRepository roomRepository;

    private final AccountService accountService;
    private final FilmService filmService;

    private final RoomMapper roomMapper;

    @Transactional
    @Override
    public RoomResponse createRoom(RoomRequest room) {
        Account account = accountService.getById(room.getAdminId());

        Room newRoom = Room.builder()
                .admin(account)
                .isActive(true)
                .code(generateCode())
                .build();

        newRoom = roomRepository.save(newRoom);

        account.getRooms().add(newRoom);
        accountService.save(account);

        return roomMapper.toResponse(newRoom);
    }

    private String generateCode(){
        String code = RandomStringUtils.randomAlphanumeric(CODE_LENGTH);

        while (roomRepository.existsRoomByCode(code)){
            code = RandomStringUtils.randomAlphanumeric(CODE_LENGTH);
        }

        return code;
    }

    @Override
    public RoomExtendedResponse getRoomExtendedResponseById(UUID roomId) {
        return  roomMapper.toExtendedResponse(
                roomRepository.findById(roomId)
                        .orElseThrow(RoomNotFoundException::new));
    }

    @Override
    public Room getRoomById(UUID roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(RoomNotFoundException::new);
    }

    @Transactional
    @Override
    public void makeRoomInactive(UUID roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotExistException(roomId));

        room.setIsActive(false);
        roomRepository.save(room);
    }

    @Transactional
    @Override
    public RoomExtendedResponse changeFilm(UUID roomId, UUID filmId) {
        Film film = filmService.getById(filmId);

        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(()
                        -> new RoomNotExistException(roomId));

        room.setCurrentFilm(film);

        return  roomMapper.toExtendedResponse(
                roomRepository.save(room));
    }

    @Override
    public Room getRoomByCode(String code) {
        return roomRepository
                .findRoomByCode(code)
                .orElseThrow(RoomNotFoundException::new);
    }
}
