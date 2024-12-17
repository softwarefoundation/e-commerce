package br.com.devchampions.ecommerce.security.repository;

import br.com.devchampions.ecommerce.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(final String username);

}
