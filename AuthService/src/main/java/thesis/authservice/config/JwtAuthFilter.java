package thesis.authservice.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthProvider userAuthProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        String PREFIX = "Bearer ";
        if (hasText(header) && header.startsWith(PREFIX)) {
            try {

                SecurityContextHolder.getContext().setAuthentication(userAuthProvider.validateToken(header.replace(PREFIX, "")));

            } catch (RuntimeException e) {

                SecurityContextHolder.clearContext();
                throw  e;
            }
        }

        filterChain.doFilter(request, response);

    }
}
