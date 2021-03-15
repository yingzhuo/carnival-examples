package carnival.examples;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.yingzhuo.carnival.restful.security.factory.AlgorithmFactories;
import com.github.yingzhuo.carnival.restful.security.factory.AlgorithmFactory;
import com.github.yingzhuo.carnival.restful.security.factory.DefaultJwtTokenFactory;
import com.github.yingzhuo.carnival.restful.security.factory.JwtTokenFactory;
import com.github.yingzhuo.carnival.restful.security.parser.JwtTokenParser;
import com.github.yingzhuo.carnival.restful.security.parser.TokenParser;
import com.github.yingzhuo.carnival.restful.security.realm.AbstractJwtUserDetailsRealm;
import com.github.yingzhuo.carnival.restful.security.token.Token;
import com.github.yingzhuo.carnival.restful.security.userdetails.UserDetails;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ServletComponentScan
public class AppBootSecurity implements WebMvcConfigurer {

    @Bean
    public AlgorithmFactory algorithmFactory() {
        return AlgorithmFactories.hmac256(AppBootSecurity.class.getName());
    }

    @Bean
    public TokenParser tokenParser() {
        return new JwtTokenParser();
    }

    @Bean
    public JwtTokenFactory jwtTokenFactory(AlgorithmFactory algorithmFactory) {
        return new DefaultJwtTokenFactory(algorithmFactory.create());
    }

    @Component
    protected static class UserDetailsRealms extends AbstractJwtUserDetailsRealm {
        @Override
        protected UserDetails getUserDetails(Token token, DecodedJWT jwt) {
            return UserDetails.builder()
                    .username(jwt.getClaim("username").asString())
                    .build();
        }
    }

}
