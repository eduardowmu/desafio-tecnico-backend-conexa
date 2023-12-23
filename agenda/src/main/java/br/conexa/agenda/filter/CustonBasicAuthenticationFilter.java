package br.conexa.agenda.filter;

import br.conexa.agenda.model.User;
import br.conexa.agenda.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CustonBasicAuthenticationFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    private static final String AUTHORIZATION = "Authorization";
    private static final String BASIC = "Basic ";

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(isBasicAuthentication(request)) {
            String[] credentials = decodeBase64(getHeader(request).replace(BASIC, "")).split(":");
            
            String userName = credentials[0];
            String password = credentials[1];

            Optional<User> user = this.userRepository.findByUserName(userName);
            if(!user.isPresent()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("User does not exist");
                return;
            }

            boolean valid = checkPassword(user.get().getPassword(), password);
            if(!valid) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Password not match");
            }

            setAuthentication(user);
        }
    }

    private void setAuthentication(Optional<User> user) {
        Authentication authentication = createAuthentication(user.get());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Authentication createAuthentication(User user) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        return new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
    }

    private boolean checkPassword(String password, String password1) {
        return passwordEncoder().matches(password, password1);
    }

    private String decodeBase64(String base64) {
        byte[] decodeBytes = Base64.getDecoder().decode(base64);
        return new String(decodeBytes);
    }

    private boolean isBasicAuthentication(HttpServletRequest request) {
        String header = getHeader(request);
        return header != null && header.startsWith(BASIC);
    }

    private String getHeader(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }
}