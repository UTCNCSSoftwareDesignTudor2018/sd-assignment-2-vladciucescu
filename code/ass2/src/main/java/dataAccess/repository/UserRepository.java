package dataAccess.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dataAccess.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByName(String name);
	public Optional<User> findByEmail(String email);
}
