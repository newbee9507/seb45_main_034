package com.seb_main_034.SERVER.users.service;

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

    public Users update(Long userId, Users user) {
        log.info("Service 호출 -> 업데이트");
        Users findUser = findById(userId);

        user.setUserId(findUser.getUserId());
        user.setEmail(findUser.getEmail());

        return repository.save(user);
    }

    public void delete(Users user) {
        log.info("Service 호출 -> 삭제");
        repository.delete(user);
    }

    public Users findById(Long userId) {
        log.info("findById 호출");
        return repository.findById(userId).orElseThrow(NoSuchElementException::new);
    }


}
