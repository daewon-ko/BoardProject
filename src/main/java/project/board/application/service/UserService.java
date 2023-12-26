package project.board.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.board.application.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
}
