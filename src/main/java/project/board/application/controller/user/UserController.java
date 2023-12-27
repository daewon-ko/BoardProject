package project.board.application.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.board.application.controller.user.request.UserSignInRequest;
import project.board.application.controller.user.request.UserSignUpRequest;
import project.board.application.service.UserService;
import project.board.domain.User;

@Controller
@RequiredArgsConstructor
@Slf4j
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
    @ResponseBody
    public String signIn(@RequestBody @Valid UserSignInRequest dto, Model model, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "users/sign-in";
        }
        User user = userService.signIn(dto);
        //TODO : 위의 user 객체에 대하여 null check를 분기문으로 할 필요가 있을까?
        /**
         * user는 Repository Layer에서 Optional로 감싸서 반환한 것을 Service Layer에서 Null check를 해준다.
         * 그럼에도 하단과 같이 null CHECK를 해줘야할까?
         */
        if (user != null) {

            model.addAttribute("user", user.getUserId());
            HttpSession session = request.getSession();
            session.setAttribute("user", user.getUserId());
            session.setMaxInactiveInterval(1800);
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
