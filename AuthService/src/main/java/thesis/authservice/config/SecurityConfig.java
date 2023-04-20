package thesis.authservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import thesis.authservice.exceptionHandler.ControllerAdvice;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private ControllerAdvice controllerAdvice;

    @Autowired
    private UserAuthenticationEntryPoint userAuthenticationEntryPoint;

    @Autowired
    private UserAuthProvider userAuthProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/swagger-ui/**", "/auth/login", "/auth/register",
                        "/reset", "/reset/password", "/recommendation/api-docs/**", "/auth/validate", "/auth/{id}")
                .permitAll()
                .requestMatchers("/auth/google/sign-in")
                .authenticated().and()
                .oauth2Login()
                .and()
                .oauth2Client()
                .and()
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
//                .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint).and()
                .addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class);
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


//        http
//
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/auth/google/sign-in")
//                .authenticated().and()
//                .oauth2Login().and()
//                .oauth2Client()
//                .and().authorizeHttpRequests()
//                .requestMatchers("/swagger-ui/**", "/auth/login", "/auth/register",
//                        "/reset", "/reset/password", "/recommendation/api-docs/**", "/auth/validate")
//                .permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint).and()
//                .addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class);
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


//        http
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/swagger-ui/**", "/auth/login", "/auth/register",
//                        "/reset", "/reset/password", "/recommendation/api-docs/**", "/auth/validate", "/auth/{id}")
//                .permitAll()
//                .requestMatchers("/auth/google/sign-in")
//                .authenticated().and()
//                .oauth2Login()
//                .and()
//                .oauth2Client()
//                .and()
//                .authorizeHttpRequests()
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class);
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        return http.build();

    }
}