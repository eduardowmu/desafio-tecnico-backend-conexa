package br.conexa.agenda.filter;

import br.conexa.agenda.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class UserPrincipal {
    private String userName;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private UserPrincipal(User user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.authorities = user.getRoles().stream().map(role -> {
            return new SimpleGrantedAuthority("ROLE ".concat(role.getName()));
        }).collect(Collectors.toList());
    }

    public static UserPrincipal create(User user) {
        return new UserPrincipal(user);
    }
}