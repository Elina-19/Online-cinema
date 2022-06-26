package ru.itis.utils;

import lombok.experimental.UtilityClass;
import ru.itis.exception.InvalidPageSizeException;

@UtilityClass
public class PageSizeUtil {

    public static Integer processPageSize(Integer pageSize, Integer defaultPageSize) {
        if (pageSize == null) {
            return defaultPageSize;
        }
        if (pageSize < 1) {
            throw new InvalidPageSizeException("Page size must be greater than 0");
        }

        return pageSize;
    }
}
