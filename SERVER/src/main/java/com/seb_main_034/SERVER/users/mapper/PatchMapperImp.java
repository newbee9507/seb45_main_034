package com.seb_main_034.SERVER.users.mapper;

import com.seb_main_034.SERVER.users.dto.UserPatchDto;
import com.seb_main_034.SERVER.users.entity.Users;

public class PatchMapperImp implements PatchMapper{

    @Override
    public Users UserPatchDTOtoUser(Users user, UserPatchDto userPatchDto) {

        if (userPatchDto == null) {
            return user;
        }

        if (userPatchDto.getNickName() != null) {
            user.setNickName( userPatchDto.getNickName() );
        }
        if (userPatchDto.getProFilePicture() != null) {
            user.setProFilePicture( userPatchDto.getProFilePicture() );
        }


        return user;
    }
}
