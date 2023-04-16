package thesis.authservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

//    @Autowired
//    private UserAuthenticationEntryPoint userAuthenticationEntryPoint;

    @Autowired
    private UserAuthProvider userAuthenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeHttpRequests()
                        .requestMatchers("/swagger-ui/**", "/login", "/register",
                "/reset", "/reset/password", "/recommendation/api-docs/**")
                .permitAll()
                .requestMatchers("/google/sign-in")
                .authenticated().and()
                .oauth2Login()
                .and()
                .oauth2Client()
                .and()
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class);
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)


        return http.build();
    }
}