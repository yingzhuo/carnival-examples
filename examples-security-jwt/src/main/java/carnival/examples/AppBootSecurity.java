package carnival.examples;

import com.github.yingzhuo.carnival.security.jwt.algorithm.AlgorithmFactories;
import com.github.yingzhuo.carnival.security.jwt.algorithm.AlgorithmFactory;
import com.github.yingzhuo.carnival.security.token.resolver.TokenResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
class AppBootSecurity extends WebSecurityConfigurerAdapter {

    @Bean
    AlgorithmFactory algorithmFactory() {
        return AlgorithmFactories.hmac384(AppBootSecurity.class.getName());
    }

    @Bean
    TokenResolver tokenResolver() {
        return TokenResolver.builder()
                .bearerToken()
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTION"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.csrf()
                .disable();

        http.cors(Customizer.withDefaults());

        http.httpBasic()
                .disable();

        http.jee()
                .disable();

        http.formLogin()
                .disable();

        http.logout()
                .disable();

        http.rememberMe()
                .disable();

        http.x509()
                .disable();

        http.anonymous();

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/v1/security/login").permitAll()
                .antMatchers(HttpMethod.GET, "/v1/template/**").permitAll()
                .antMatchers(HttpMethod.GET, "/favicon.ico", "/**/favicon.ico").permitAll()
                .antMatchers(HttpMethod.GET, "/static/**").permitAll()
                .antMatchers(HttpMethod.GET, "/css/**").permitAll()
                .antMatchers(HttpMethod.GET, "/js/**").permitAll()
                .antMatchers(HttpMethod.GET, "/image/**").permitAll()
                .antMatchers("/actuator/**").hasRole("ADMIN")
                .antMatchers("/swagger-ui.html", "/swagger-ui/**").permitAll()
                .antMatchers("/api-docs/**").permitAll()
                .anyRequest().fullyAuthenticated();

        http.exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.setStatus(HttpServletResponse.SC_UNAUTHORIZED));
    }

}
