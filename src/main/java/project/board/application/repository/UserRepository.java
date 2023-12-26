package project.board.application.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.board.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
