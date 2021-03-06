package ru.itis.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@ToString(callSuper = true, exclude = "accounts")
@EqualsAndHashCode(callSuper = true, exclude = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Room extends AbstractEntity{

    /**Активна или нет комната*/
    @Column(name = "is_active")
    private Boolean isActive;

    /**Код для присоединения к комнате*/
    @Column(nullable = false, unique = true, columnDefinition = "boolean default true")
    private String code;

    @ManyToMany(mappedBy = "rooms")
    private Set<Account> accounts;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account admin;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film currentFilm;
}
