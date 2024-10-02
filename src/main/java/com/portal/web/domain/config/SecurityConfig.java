package com.portal.web.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.portal.web.domain.service.AccountServiceJPA;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public UserDetailsService userDetailsService(){
        return new AccountServiceJPA();
    }

// adminer - pass
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(
                            "/api/account/new-user",
                            "/api/account/reg",
                            "/reg", "/api/account/register", "/test",
                            "/resources/**",
                            "/static/**",
                            "/css/**",
                            "/assets/images/**",
                            "/images/**"
                    ).permitAll()
                    .requestMatchers("/**").authenticated()
            )
            .formLogin(AbstractAuthenticationFilterConfigurer::permitAll);

    httpSecurity
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/perform_login")
            .defaultSuccessUrl("/start", true);
    return httpSecurity.build();
}

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider =  new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
