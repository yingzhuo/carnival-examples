package carnival.examples.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.yingzhuo.carnival.security.authentication.JwtAuthenticationProvider;
import com.github.yingzhuo.carnival.security.userdetails.UserDetailsPlus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
class JwtAuthenticationComponent extends JwtAuthenticationProvider {

    public JwtAuthenticationComponent(Algorithm algorithm) {
        super(algorithm);
    }

    @Override
    protected UserDetails doAuthenticate(String rawToken, DecodedJWT jwt) throws AuthenticationException {
        return UserDetailsPlus.builder()
                .id(jwt.getClaim("userId").asLong())
                .username(jwt.getClaim("username").asString())
                .roles(jwt.getClaim("roles").asArray(String.class))
                .build();
    }

}
