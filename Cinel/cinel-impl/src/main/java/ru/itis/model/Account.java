package ru.itis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Account extends AbstractEntity{
    @Column(unique = true, nullable = false)
    private String username;

    private String email;
    @Column(name = "hash_password", nullable = false)

    private  String hashPassword;

    /**Пользователь активен/удален*/
    private Boolean state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    /**Для oAuth*/
    private String token;

    /**Для подтверждения по почте*/
    private Boolean confirmed;

    /**Дата окончания(?) жизни ссылки на почте*/
    @Column(name = "code_expires")
    private Timestamp codeExpires;

    /**Код ссылки на почте*/
    private String code;

    private Favourite favourite;

    @ManyToOne
    @JoinTable(joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
    @JoinColumn(name = "room_id", referencedColumnName = "id"))
    private Room room;
}
