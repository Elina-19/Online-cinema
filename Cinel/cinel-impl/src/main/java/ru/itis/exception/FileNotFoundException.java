package ru.itis.exception;

public class FileNotFoundException extends CinelNotFoundException{

    public FileNotFoundException() {
        super("File not found");
    }
}
