package ru.itis.exception;

public class CountryNotFoundException extends CinelNotFoundException {

    public CountryNotFoundException() {
        super("Country not found");
    }
}
