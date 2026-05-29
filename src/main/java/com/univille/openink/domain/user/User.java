package com.univille.openink.domain.user;

import com.univille.openink.domain.user.dto.CreateUserRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 150)
    private String name;

    public User(CreateUserRequest data){
        this.name = data.name();
    }

}
