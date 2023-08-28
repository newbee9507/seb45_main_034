package com.seb_main_034.SERVER.users.controller;


import com.seb_main_034.SERVER.users.dto.UserPatchDto;
import com.seb_main_034.SERVER.users.dto.UserSaveDto;
import com.seb_main_034.SERVER.users.entity.Users;
import com.seb_main_034.SERVER.users.mapper.UserMapper;
import com.seb_main_034.SERVER.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @GetMapping("/info/{userId}")
    public ResponseEntity<Users> info(@PathVariable @Positive Long userId) {
        log.info("Controller 호출 -> 회원조회");
        Users findUser = service.findById(userId);

        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    @PostMapping("/create") // 회원가입
    public ResponseEntity<Users> signUp(@RequestBody UserSaveDto userSaveDto) {
        log.info("Controller 호출 -> 회원가입");
        log.info("가입정보 -> 이메일={},비밀번호={}", userSaveDto.getEmail(), userSaveDto.getPassWord());

        Users createUsers = mapper.UserSaveDTOtoUser(userSaveDto);
        service.save(createUsers);

        return new ResponseEntity(createUsers, HttpStatus.CREATED);
    }

    @PatchMapping("/edit/{userId}") // 회원정보 수정
    public ResponseEntity<Users> update(@PathVariable @Positive Long userId,
                                        @Valid @RequestBody UserPatchDto userPatchDto) {
        log.info("Controller 호출 -> 회원정보 수정");
        log.info("수정정보 -> 비밀번호={}, 닉네임={}, 프로필사진={}",
                userPatchDto.getPassWord(), userPatchDto.getUserName(), userPatchDto.getProFile());

        userPatchDto.setUserId(userId);
        Users updateUser = service.update(mapper.UserPatchDTOtoUser(userPatchDto));

        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity delete(@PathVariable @Positive Long userId) {
        log.info("Controller 호출 -> 회원탈퇴");

        Users findUser = service.findById(userId);
        service.delete(findUser);

        return new ResponseEntity("삭제 완료", HttpStatus.OK);
    }
}
