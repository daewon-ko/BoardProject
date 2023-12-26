package project.board.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import project.board.application.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

}
