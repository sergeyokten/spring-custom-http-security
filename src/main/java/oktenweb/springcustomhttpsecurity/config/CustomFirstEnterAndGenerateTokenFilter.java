package oktenweb.springcustomhttpsecurity.config;

import oktenweb.springcustomhttpsecurity.models.User;
import oktenweb.springcustomhttpsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomFirstEnterAndGenerateTokenFilter extends AbstractAuthenticationProcessingFilter {

    private UserService userService;

    AuthenticationManager authenticationManager;

    public CustomFirstEnterAndGenerateTokenFilter(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder encoder) {
        super(new AntPathRequestMatcher("/login", "POST"));
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = encoder;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        System.out.println("attemptAuthentication");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + " " + password + "--------------------------");
        UserDetails userDetails = userService.loadUserByUsername(username);

        System.out.println(request);
        System.out.println(response);
        if (!passwordEncoder.matches(password, password)) {
            System.out.println("good");
        }
        response.setHeader("tokena", "!!!" + userDetails.getUsername() + "!!!");
        System.out.println(userDetails.getUsername());

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication");
        response.setHeader("tokenb", "!!!" + authResult.getName() + "!!!");
//        doFilter(request, response, chain);

    }
}
