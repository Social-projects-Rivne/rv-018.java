package ua.softserve.rv_018.greentourism.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.softserve.rv_018.greentourism.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByLogin(String login);
}