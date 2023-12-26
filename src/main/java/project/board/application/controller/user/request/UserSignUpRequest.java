package project.board.application.controller.user.request;

import lombok.Getter;
import project.board.domain.User;

@Getter
public class UserSignUpRequest {
    private String name;
    private String email;
    private String nickname;
    private String password;

    public  User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .nickname(this.nickname)
                .password(this.password)
                .build();
    }


}
