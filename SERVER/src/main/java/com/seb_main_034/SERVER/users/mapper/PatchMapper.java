package com.seb_main_034.SERVER.users.mapper;

import com.seb_main_034.SERVER.users.dto.UserPatchDto;
import com.seb_main_034.SERVER.users.entity.Users;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public interface PatchMapper {

    @Bean
    Users UserPatchDTOtoUser(Users user, UserPatchDto userPatchDto);
}
