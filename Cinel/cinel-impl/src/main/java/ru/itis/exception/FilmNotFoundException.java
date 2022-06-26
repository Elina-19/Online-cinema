package ru.itis.exception;

public class FilmNotFoundException extends CinelNotFoundException {

    public FilmNotFoundException() {
        super("Film not found");
    }
}
