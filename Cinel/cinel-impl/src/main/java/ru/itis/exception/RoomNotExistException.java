package ru.itis.exception;

import java.util.UUID;

public class RoomNotExistException extends CinelNotExistException {
    public RoomNotExistException(UUID roomId){
        super("Room with id " + roomId + "does not exist");
    }
}
