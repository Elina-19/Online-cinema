package ru.itis.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account_refresh_token")
public class AccountRefreshTokenEntity extends RefreshTokenEntity {

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
