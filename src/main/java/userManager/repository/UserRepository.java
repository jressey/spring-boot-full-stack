package userManager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import userManager.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

}