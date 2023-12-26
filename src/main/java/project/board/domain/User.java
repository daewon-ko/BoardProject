package project.board.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    private long id;
    private String name;
    private String email;
    private String nickname;

    private String password;

    private String profileImage;


}
