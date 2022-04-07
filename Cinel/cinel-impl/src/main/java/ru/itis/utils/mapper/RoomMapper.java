package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import ru.itis.dto.response.RoomExtendedResponse;
import ru.itis.dto.response.RoomResponse;
import ru.itis.model.Room;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomResponse toResponse(Room room);
    RoomExtendedResponse toExtendedResponse(Room room);
}
