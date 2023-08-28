package com.seb_main_034.SERVER.users.service;

import com.seb_main_034.SERVER.users.dto.PasswordDto;
import com.seb_main_034.SERVER.users.dto.UserPatchDto;
import com.seb_main_034.SERVER.users.entity.Users;
import com.seb_main_034.SERVER.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository repository;

    public Users save(Users user) {
        log.info("Service 호출 -> 저장");
        return repository.save(user);
    }

    public Users update(Users user) {
        log.info("Service 호출 -> 업데이트");

        return repository.save(user);
    }

    public void delete(Users user) {
        log.info("Service 호출 -> 삭제");
        repository.delete(user);
    }

    public void changePw(Long userId, PasswordDto passwordDto) {
        log.info("Service 호출 -> 비밀번호 변경");
        Users findUser = findById(userId);
        findUser.setPassWord(passwordDto.getPassWord());
        repository.save(findUser);
    }

    public void deleteAll() {
        log.info("Service 호출 -> 전부 삭제");
        repository.deleteAll();
    }

    public Users findById(Long userId) {
        log.info("findById 호출");
        return repository.findById(userId).orElseThrow(NoSuchElementException::new);
    }
}
