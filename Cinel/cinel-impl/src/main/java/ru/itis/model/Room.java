package ru.itis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Room extends AbstractEntity{

    /**Активна или нет комната*/
    private Boolean state;

    /**Код для присоединения к комнате*/
    private String code;

    @OneToMany(mappedBy = "room")
    private List<Account> accounts;
}
