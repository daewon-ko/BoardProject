package project.board.application.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.board.application.controller.user.request.UserSignInRequest;
import project.board.application.controller.user.request.UserSignUpRequest;
import project.board.application.service.UserService;
import project.board.domain.User;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users/sign-up")
    public String signUp() {
        return "/users/sign-up";
    }

    @PostMapping("/users/sign-up")
    public String signUP(@Valid @ModelAttribute UserSignUpRequest dto, Model model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ":/users/sign-up";
        }
        User savedUser = userService.createUser(dto);
        model.addAttribute("user", savedUser);
        return "redirect:/";
    }

    @GetMapping("/users/sign-in")
    public String signIn() {
        return "users/sign-in";
    }

    @PostMapping("/users/sign-in")
    public String signIn(@Valid @ModelAttribute("user") UserSignInRequest dto, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/sign-in";
        }
        User user = userService.signIn(dto);
        if (user != null) {
            model.addAttribute("user", user.getName());
            return "redirect:/";
        }
        //TODO : Exception이 Service Layer에서 나온다 해도 아래 코드는 유의미한가?
        /**
         * Service Layer에서 Null 탐지를 한다면 Exception을 던질 것
         * GlobalExcepaionHandler 등을 통해서 잡는다면 아래까지 도달 할 수 있는가?
         */
        model.addAttribute("error", "loginFail");
        return "redirect:/";
    }
}
