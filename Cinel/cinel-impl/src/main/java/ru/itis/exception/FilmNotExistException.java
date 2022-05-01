package ru.itis.exception;

import java.util.UUID;

public class FilmNotExistException extends CinelNotExistException{
    public FilmNotExistException(UUID filmId){
        super("Film with id " + filmId + "does not exist");
    }
}
