package com.aplikacja.herbaciarnia.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

import static org.springframework.security.web.util.matcher.RegexRequestMatcher.regexMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {


    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Bean
    public PasswordEncoder BCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder BCryptPasswordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(BCryptPasswordEncoder);

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withUsername("user")
                .password(BCryptPasswordEncoder().encode("user1"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(BCryptPasswordEncoder().encode("admin1"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(Arrays.asList(user, admin));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf ->csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.GET, "/orders").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/orders").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/orders").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/orders").hasAnyRole("USER", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/teas").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/teas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/teas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/teas").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/customers").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/customers").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/customers").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/customers").hasAnyRole("USER", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/addresses").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/addresses").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/addresses").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/addresses").hasAnyRole("USER", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/user").hasRole("USER")
                        .requestMatchers("/user").hasRole("USER")
                        .requestMatchers("/admin").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/login").permitAll()
                        .requestMatchers(regexMatcher(".*\\?x=y")).hasRole("SPECIAL") // matches /any/path?x=y
                        .anyRequest().authenticated())

                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }
}
