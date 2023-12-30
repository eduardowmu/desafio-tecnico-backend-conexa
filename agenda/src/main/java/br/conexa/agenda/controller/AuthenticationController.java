package br.conexa.agenda.controller;
import br.conexa.agenda.dto.AttendanceDto;
import br.conexa.agenda.dto.LoginResponseDto;
import br.conexa.agenda.dto.AuthenticationDto;
import br.conexa.agenda.dto.RegisterDto;
import br.conexa.agenda.exception.IllegalArgumentException;
import br.conexa.agenda.model.User;
import br.conexa.agenda.security.TokenService;
import br.conexa.agenda.service.AttendanceService;
import br.conexa.agenda.service.impl.RegisterServiceImpl;
import br.conexa.agenda.utils.EntityUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RegisterServiceImpl registerService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data){
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.userName(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDto(token));
        }catch (RuntimeException e) {
            throw new IllegalArgumentException("Usuário ou senha inválida");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity register(@RequestBody @Valid RegisterDto data){
        this.registerService.create(data);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/attendance")
    public ResponseEntity<AttendanceDto> create(@RequestBody AttendanceDto data,
                                                @RequestHeader("Authorization") String token) {
        this.tokenService.findByToken(token.replaceAll("Bearer ", ""));
        return ResponseEntity.ok().body(EntityUtils.toAttendanceDto(this.attendanceService.create(data)));
    }

    @GetMapping("attendances")
    public ResponseEntity<List<AttendanceDto>> listAll(@RequestHeader("Authorization") String token) {
        this.tokenService.findByToken(token.replaceAll("Bearer ", ""));
        List<AttendanceDto> attendanceDtos = new ArrayList<>();
        this.attendanceService.listAll().forEach(a -> attendanceDtos.add(EntityUtils.toAttendanceDto(a)));
        return ResponseEntity.ok().body(attendanceDtos);
    }

    @DeleteMapping("/logoff")
    public void logoff(@RequestHeader("Authorization") String token) {
        this.tokenService.deleteToken(token.replaceAll("Bearer ", ""));
    }
}