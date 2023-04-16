package thesis.authservice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import thesis.authservice.dto.UserDto;
import thesis.authservice.service.UserService;

import java.security.Key;
import java.util.*;

@Component
public class UserAuthProvider {


    @Value("${jwt.public.key}")
    private String jwtSecret;

    @Autowired
    private UserService userService;

    public Authentication validateToken(String token) {

        Claims claims = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();

        UserDto userDto = userService.findByLogin(claims.getSubject());

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userDto.getRoles()));

        return new UsernamePasswordAuthenticationToken(userDto, null, authorities);
    }

    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String userName) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(new Date().getTime() + 3_600_000))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//    @PostConstruct
//    protected void init(){
//
//        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
//    }

//        public String createToken (String login) {
//
//            Date now = new Date();
//            Date expire = new Date(now.getTime() + 3_600_000);
//
//            return JWT.create()
//                    .withIssuer(login)
//                    .withIssuedAt(now)
//                    .withExpiresAt(expire)
//                    .sign(Algorithm.HMAC256(jwtSecret));
//        }
//
//        public Authentication validateToken(String token){
//
//            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtSecret)).build();
//
//            DecodedJWT decoded = verifier.verify(token);
//
//            UserDto userDto = userService.findByLogin(decoded.getIssuer());
//
//            List<GrantedAuthority> authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority(userDto.getRoles()));
//
//            return new UsernamePasswordAuthenticationToken(userDto, null, authorities);
//
//
//        }


}
