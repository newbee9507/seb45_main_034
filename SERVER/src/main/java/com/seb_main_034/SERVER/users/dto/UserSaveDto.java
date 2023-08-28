package com.seb_main_034.SERVER.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class UserSaveDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Length(min = 8)
    private String passWord;

    @Length(min = 2)
    private String userName;

    private String proFile;
}
