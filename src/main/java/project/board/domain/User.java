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
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "nickname", unique = true)
    private String nickname;
    @Column(name = "password")
    private String password;
    @Lob
    private String profileImage;


}
