package com.udacity.jwdnd.c1.review.config;

import com.udacity.jwdnd.c1.review.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // -- old way
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(this.authenticationService);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/signup", "/css/**", "/js/**").permitAll()
//                .anyRequest().authenticated();
//
//        // Generates a login form at /login and allows anyone to access it
//        http.formLogin() // we can provide our own template and controller if we want to customize the login, but for now we are using the default
//                .loginPage("/login")
//                .permitAll();
//
//        // Redirects successful logins to the /home page
//        http.formLogin()
//                .defaultSuccessUrl("/chat", true);
//    }


    // -- new way

    /**
     * Below code do same thing as line http.authenticationProvider() in securityFilterChain() , we can see by looking in library code
     */
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(authenticationService);
//        return authenticationManagerBuilder.build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http.authorizeRequests() --> deprecated use authorizeHttpRequests() instead
//                .antMatchers --> deprecated use requestMatchers() instead

        /*
            Below config is to open h2-console, should not be used on production.
             - By default, with spring security h2-console is blocked.
             - By default, Spring Security will protect against CRSF attacks.
             - Since the H2 database console runs inside a frame, you need to enable this in Spring Security.
             - If Spring MVC is used, then by default auth.requestMatchers() will use the MvcRequestMatcher.
               Since H2 console is not controlled by Spring MVC, we must use AntPathRequestMatcher.
         */
        http
                .authorizeHttpRequests()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll();
        http.headers().frameOptions().disable();
        http.csrf().disable();

        http
                .authorizeHttpRequests()
                .requestMatchers("/signup", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();

        http
                .formLogin()
                .loginPage("/login").permitAll();

        http.
                formLogin()
                .defaultSuccessUrl("/chat", true);

        http.authenticationProvider(authenticationService);

        return http.build();
    }
}
