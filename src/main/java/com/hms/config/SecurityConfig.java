package com.hms.config;
import com.hms.entity.User;
import com.hms.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Resource
    private DataSource dataSource;
    @Resource
    private UserService userService;
    @Resource
    private UserDetailsService userDetailsService;
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }
    @Bean
    public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry)->{
                    registry
                            .requestMatchers("/", "/static/**", "/auth/**").permitAll()
                            .requestMatchers("/admin/**").hasRole("admin")
                            .requestMatchers("/teacher/**").hasRole("teacher")
                            .requestMatchers("/student/**").hasRole("student")
                            .anyRequest().hasAnyRole("admin", "teacher", "student");
                })
                .formLogin((FormLoginConfigurer<HttpSecurity> config)->{
                    config
                            .loginPage("/auth/login")
                            .loginProcessingUrl("/auth/login")
                            .successHandler((HttpServletRequest req, HttpServletResponse resp, Authentication auth)->{
                                HttpSession session = req.getSession();
                                User user = userService.selectUserByUsername(auth.getName());
                                session.setAttribute("user", user);
                                if (Objects.equals(user.getRole(), "admin") == true) {
                                    resp.sendRedirect("/admin/index");
                                }
                                else if (Objects.equals(user.getRole(), "teacher") == true) {
                                    resp.sendRedirect("/teacher/index");
                                }
                                else if (Objects.equals(user.getRole(), "student") == true) {
                                    resp.sendRedirect("/student/index");
                                }
                            });
                })
                .logout((LogoutConfigurer<HttpSecurity> config)->{
                    config
                            .logoutUrl("/auth/logout")
                            .logoutSuccessUrl("/auth/login");
                })
                .rememberMe((RememberMeConfigurer<HttpSecurity> config)->{
                    config
                            .alwaysRemember(false)
                            .tokenValiditySeconds(60 * 60 * 24 * 7)
                            .tokenRepository(persistentTokenRepository());
                })
                .csrf((CsrfConfigurer<HttpSecurity> config)->{
                    config.disable();
                })
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        provider.setHideUserNotFoundExceptions(false);
        return new ProviderManager(provider);
    }
}