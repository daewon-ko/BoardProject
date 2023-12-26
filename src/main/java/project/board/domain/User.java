package project.board.domain;

import jakarta.persistence.*;
import lombok.*;
import project.board.util.BaseTimeEntity;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User extends BaseTimeEntity {
    @Id
    private Long id;
    @Column(name = "userId", unique = true)
    private String userId;
    @Column(name = "email")
    private String email;
    @Column(name = "nickname", unique = true)
    private String nickname;
    @Column(name = "password")
    private String password;
    @Lob
    private String profileImage;

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }


}
