package co.develhope.testcontroller.repositories;

import co.develhope.testcontroller.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}