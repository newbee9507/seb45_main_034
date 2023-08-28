package com.seb_main_034.SERVER.users.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
@Getter
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Email
    private String email;

    private String passWord;

    @Length(min = 4)
    private String userName;

    private String proFile;

    public Users() {
    }
}
