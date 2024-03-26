package com.aplikacja.herbaciarnia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Cacheable
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "users")
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @NotBlank(message = "Podaj username")
    @NotEmpty(message = "Podaj username")
    @Size(min = 3, max = 40)
    private String username;

    @NotBlank(message = "Podaj email")
    @NotEmpty(message = "Podaj email")
    @Size(min = 3, max = 40)
    private String email;

    @NotBlank(message = "Podaj hasło")
    @NotEmpty(message = "Podaj hasło")
    @Size(min = 6, max = 15)
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
