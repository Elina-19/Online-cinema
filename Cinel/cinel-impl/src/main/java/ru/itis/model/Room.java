package ru.itis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Room extends AbstractEntity{
    /**Активна или нет комната*/
    private Boolean state;

    /**Код для присоединения к комнате*/
    private String code;

    @OneToMany(mappedBy = "room")
    private List<Account> accounts;
}
