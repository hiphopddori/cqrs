package ddori.spring.query;

import ddori.spring.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMapper {
    public Optional<User> findById(long id);
}
