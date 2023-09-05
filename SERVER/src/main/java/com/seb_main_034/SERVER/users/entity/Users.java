package com.seb_main_034.SERVER.users.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Length(min = 8, max = 200)
    @Column(nullable = false)
    private String password;

    @Length(min = 2)
    @Column(nullable = false, unique = true)
    private String nickName;

    private String proFilePicture;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public Users() {
    }

    public Users(String email) {
        this.email = email;
    } // 소셜로그인시 필요함
}
