package project.board.application.controller.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import project.board.domain.User;

@Getter
public class UserSignUpRequest {
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)[a-z\\d]{5,15}$", message = "아이디는 5자 이상 15자 이하이며 영문소문자와 숫자를 반드시 포함해야합니다.")
    private String userId;

    // TODO: @Email VS @Pattern 직접 작성 차이는 어떻게 되는가?
//    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "이메일 형식이 올바르지 않습니다")
    @Email @NotEmpty
    private String email;
    private String nickname;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,16}$",
            message = "비밀번호는 8자 이상 16자 이하로, 영문, 숫자, 특수문자를 최소 1개씩 포함해야 합니다")
    private String password;

    public  User toEntity() {
        return User.builder()
                .userId(this.userId)
                .email(this.email)
                .nickname(this.nickname)
                .password(this.password)
                .build();
    }


}
