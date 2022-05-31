package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.request.MessageRequest;
import ru.itis.dto.response.MessageResponse;
import ru.itis.dto.response.RoomExtendedResponse;
import ru.itis.dto.response.RoomResponse;
import ru.itis.exception.RoomNotExistException;
import ru.itis.exception.RoomNotFoundException;
import ru.itis.model.Account;
import ru.itis.model.Film;
import ru.itis.model.Room;
import ru.itis.repository.RoomRepository;
import ru.itis.security.userdetails.AccountUserDetails;
import ru.itis.service.AccountService;
import ru.itis.service.FilmService;
import ru.itis.service.RoomService;
import ru.itis.utils.mapper.RoomMapper;

import java.security.Principal;
import java.time.LocalDateTime;
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
    public RoomResponse createRoom(UUID accountId) {
        Account account = accountService.getById(accountId);

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

    private String generateCode() {
        String code = RandomStringUtils.randomAlphanumeric(CODE_LENGTH);

        while (roomRepository.existsRoomByCode(code)) {
            code = RandomStringUtils.randomAlphanumeric(CODE_LENGTH);
        }

        return code;
    }

    @Override
    public RoomExtendedResponse getRoomExtendedResponseById(UUID roomId) {
        return roomMapper.toExtendedResponse(
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
    public void makeRoomInactive(UUID accountId, UUID roomId) {
        Room room = roomRepository.findByIdAndAdminId(roomId, accountId)
                .orElseThrow(() -> new RoomNotExistException(roomId));

        room.setIsActive(false);
        roomRepository.save(room);
    }

    @Transactional
    @Override
    public RoomExtendedResponse changeFilm(UUID accountId, UUID roomId, UUID filmId) {
        Film film = filmService.getById(filmId);

        Room room = roomRepository
                .findByIdAndAdminId(roomId, accountId)
                .orElseThrow(()
                        -> new RoomNotExistException(roomId));

        room.setCurrentFilm(film);

        return roomMapper.toExtendedResponse(
                roomRepository.save(room));
    }

    @Override
    public Room getRoomByCode(String code) {
        return roomRepository
                .findRoomByCode(code)
                .orElseThrow(RoomNotFoundException::new);
    }

    @Override
    public MessageResponse sendMessageToRoom(UUID roomId, MessageRequest message, Principal principal) {
        if (roomRepository.findById(roomId).isEmpty()) {
            throw new RoomNotExistException(roomId);
        }

        PreAuthenticatedAuthenticationToken token = (PreAuthenticatedAuthenticationToken) principal;
        AccountUserDetails userDetails = (AccountUserDetails) token.getPrincipal();

        return MessageResponse.builder()
                .text(message.getText())
                .roomId(roomId.toString())
                .timeSent(LocalDateTime.now().toString())
                .senderId(userDetails.getId())
                .build();
    }
}
