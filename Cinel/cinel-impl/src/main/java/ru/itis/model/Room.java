package ru.itis.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;


@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
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
