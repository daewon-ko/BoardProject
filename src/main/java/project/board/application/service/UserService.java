package project.board.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.board.application.controller.user.request.UserSignInRequest;
import project.board.application.controller.user.request.UserSignUpRequest;
import project.board.application.repository.UserRepository;
import project.board.domain.User;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User createUser(final UserSignUpRequest dto) {
        User entity = dto.toEntity();
        return userRepository.save(entity);
    }

    @Transactional
    public User signIn(final UserSignInRequest dto) {
        String password = dto.getPassword();
        User user = userRepository.findUserByName(dto.getUserId()).orElseThrow(() -> new IllegalStateException("해당하는 회원이 존재하지 않습니다."));

        if (!user.checkPassword(password)) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }

}
