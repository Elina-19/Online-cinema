package ru.itis.exception;

public class RoomNotFoundException extends CinelNotFoundException{
    public RoomNotFoundException(){
        super("Room not found");
    }
}
