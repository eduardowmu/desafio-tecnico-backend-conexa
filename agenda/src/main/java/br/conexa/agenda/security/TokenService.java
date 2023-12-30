package br.conexa.agenda.security;

import br.conexa.agenda.exception.IllegalArgumentException;
import br.conexa.agenda.model.TokenLogin;
import br.conexa.agenda.repository.TokenRepository;
import br.conexa.agenda.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import br.conexa.agenda.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@PropertySource("classpath:application.properties")
@Service
public class TokenService {
    @Value("${api.token.secret}")
    private String secret;

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    public TokenService(TokenRepository tokenRepository, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername())
                    //.withExpiresAt(genExpirationDate())
                    .sign(algorithm);

            User gotUser = this.userRepository.findByUserName(user.getUsername()).orElseThrow(
                    () -> new IllegalArgumentException("Usuário não encontrado " + user.getUsername()));

            this.tokenRepository.save(new TokenLogin(null, gotUser, token));

            return token;
        } catch(JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }

    public TokenLogin findByToken(String token) {
        return this.tokenRepository.findByToken(token).orElseThrow(() -> new IllegalArgumentException("Não Autenticado"));
    }

    public void deleteToken(String token) {
        TokenLogin tokenLogin = this.tokenRepository.findByToken(token).get();
        this.tokenRepository.delete(tokenLogin);
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}