package br.conexa.agenda.repository;

import br.conexa.agenda.model.TokenLogin;
import br.conexa.agenda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenLogin, Long> {
    Optional<TokenLogin> findByToken(String token);
}