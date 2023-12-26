package br.conexa.agenda.controller;
import br.conexa.agenda.dto.LoginResponseDto;
import br.conexa.agenda.dto.AuthenticationDto;
import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.enumeration.UserRole;
import br.conexa.agenda.model.User;
import br.conexa.agenda.repository.UserRepository;
import br.conexa.agenda.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.userName(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/signup")
    public ResponseEntity register(@RequestBody @Valid RegisterDto data){
        if(this.repository.findByUserName(data.email()).isPresent()) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        User newUser = new User(null, data.email(), encryptedPassword, data.especialidade().equals("") ?
                UserRole.ADMIN : UserRole.USER);

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}