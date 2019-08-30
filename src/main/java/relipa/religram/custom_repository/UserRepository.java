package relipa.religram.custom_repository;

import org.springframework.data.jpa.repository.JpaRepository;

import relipa.religram.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);

    public User findByEmail(String email);
}