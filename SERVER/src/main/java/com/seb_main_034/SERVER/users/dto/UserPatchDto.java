package com.seb_main_034.SERVER.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
public class UserPatchDto {
    
    @Positive
    private Long userId;

    @Length(min = 8)
    private String passWord;

    @Length(min = 4)
    private String userName;

    private String proFile;
}
