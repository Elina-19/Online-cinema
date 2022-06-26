package ru.itis.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@ToString(callSuper = true, exclude = {"adminRooms", "rooms"})
@EqualsAndHashCode(callSuper = true, exclude = {"adminRooms", "rooms"})
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
    @Column(name = "is_active")
    private Boolean isActive;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    /**Для oAuth*/
    private String token;

    /**Для подтверждения по почте*/
    @Column(columnDefinition = "boolean default false")
    private Boolean confirmed;

    /**Дата и время отправления ссылки на почту*/
    @Column(name = "code_sent")
    private LocalDateTime codeSent;

    /**Код ссылки на почте*/
    @Column(nullable = false, unique = true)
    private String code;

    /**Комнаты, в которых состоит пользователь*/
    @ManyToMany
    @JoinTable(name = "account_room",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"))
    private Set<Room> rooms;

    /**Комнаты, в которых пользователь является админом*/
    @OneToMany(mappedBy = "admin")
    private Set<Room> adminRooms;

    @ManyToMany
    @JoinTable(name = "account_film",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"))
    private Set<Film> favouriteFilms;
}
