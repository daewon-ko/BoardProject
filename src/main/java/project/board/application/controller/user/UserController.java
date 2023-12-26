package project.board.application.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.board.application.controller.user.request.UserSignUpRequest;
import project.board.application.service.UserService;
import project.board.domain.User;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public String signUp() {
        return "/users/sign-up";
    }

    @PostMapping
    String signUP(@Valid @ModelAttribute UserSignUpRequest dto, Model model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ":/users/sign-up";
        }
        User savedUser = userService.createUser(dto);
        model.addAttribute("user", savedUser);
        return "redirect:/";
    }


}
