package br.com.devchampions.ecommerce.security.repository;

import br.com.devchampions.ecommerce.security.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Login, Long> {

    public Optional<Login> findByUsername(final String username);

}
