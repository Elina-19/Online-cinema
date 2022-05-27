package ru.itis.exception;

public class GenreNotFoundException extends CinelNotFoundException {

    public GenreNotFoundException() {
        super("Genre not found");
    }
}
