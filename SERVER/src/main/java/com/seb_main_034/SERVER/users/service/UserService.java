package com.seb_main_034.SERVER.users.service;

import com.seb_main_034.SERVER.exception.ExceptionCode;
import com.seb_main_034.SERVER.exception.UserException;
import com.seb_main_034.SERVER.users.dto.PasswordDto;
import com.seb_main_034.SERVER.users.entity.Users;
import com.seb_main_034.SERVER.users.repository.UserRepository;
import com.seb_main_034.SERVER.auth.utils.MyAuthorityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final MyAuthorityUtils authorityUtils; // 권한 설정을 위한 클래스

    public Users save(Users user) {
        log.info("Service 호출 -> 저장");
        if (checkEmailAndNickName(user.getEmail(), user.getNickName())) { // 이미 서버에 존재하는 이메일,닉네임인지 확인
            user.setPassword(encoder.encode(user.getPassword())); // 비밀번호 암호화
            List<String> roles = authorityUtils.createRoles(user.getEmail()); // 권한 설정
            user.setRoles(roles);
            return repository.save(user);
        }
        return null;
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
        String encodePw = encoder.encode(passwordDto.getPassword());// 바꾸길 원하는 비밀번호 암호화 및 재설정
        findUser.setPassword(encodePw);
        repository.save(findUser);
    }

    public Users findById(Long userId) {
        log.info("findById 호출");
        return repository.findById(userId).orElseThrow(() -> new UserException(ExceptionCode.USER_NOT_FOUND));
    }

    public Users findByEmail(String email) {
        log.info("이메일로 유저조회");
        Users findUser = repository.findByEmail(email).orElseThrow(
                () -> new UserException(ExceptionCode.USER_NOT_FOUND));

        return findUser;
    }

    /**
     * 회원가입시, 이메일과 닉네임의 중복을 체크하는 메서드.
     * 단, 닉네임이 null로 같아도 에러가나는데, 이 부분 수정 필요.
     */
    public boolean checkEmailAndNickName(String email, String NickName) {
        if (repository.findByEmail(email).isPresent()) {
            log.error("이미 존재하는 회원입니다.");
            throw new UserException(ExceptionCode.USER_EXISTS);
        }
        if (repository.findBynickName(NickName).isPresent()) {
            log.error("이미 존재하는 닉네임입니다.");
            throw new UserException(ExceptionCode.NICKNAME_EXISTS);
        }
        return true;
    }

    public void deleteAll() {
        log.info("Service 호출 -> 전부 삭제");
        repository.deleteAll();
    }
}
