package ru.itis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Account extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "hash_password", nullable = false)
    private String hashPassword;

    /**Пользователь активен/удален*/
    private Boolean state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    /**Для oAuth*/
    private String token;

    /**Для подтверждения по почте*/
    private Boolean confirmed;

    /**Дата и время отправления ссылки на почту*/
    @Column(name = "code_sent")
    private LocalDateTime codeSent;

    /**Код ссылки на почте*/
    private String code;

    @ManyToOne()
    @JoinColumn(name = "room_id")
    private Room room;

    public enum Role {
        ADMIN, USER
    }
}
