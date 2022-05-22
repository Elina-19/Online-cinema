package ru.itis.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Genre extends AbstractEntity{

    @Column(nullable = false, unique = true)
    private String name;
}
