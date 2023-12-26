package project.board.application.controller.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserSignInRequest {

    @NotBlank(message = "올바르게 아이디를 입력해주세요.")
    private String userId;
    @NotBlank(message = "올바르게 비밀번호를 입력해주세요.")
    private String password;

}
