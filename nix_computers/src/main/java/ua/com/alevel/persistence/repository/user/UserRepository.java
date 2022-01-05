package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.AbstractRepository;

@Repository
public interface UserRepository<U extends User> extends AbstractRepository<U> {

    U findByEmail(String email);

    boolean existsByEmail(String email);
}
