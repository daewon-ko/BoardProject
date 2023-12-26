package project.board.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

}
