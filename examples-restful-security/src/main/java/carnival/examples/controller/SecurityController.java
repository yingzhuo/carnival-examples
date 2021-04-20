package carnival.examples.controller;

import com.github.yingzhuo.carnival.restful.security.RequiresAuthentication;
import com.github.yingzhuo.carnival.restful.security.annotation.SecurityContext;
import com.github.yingzhuo.carnival.restful.security.factory.JwtTokenInfo;
import com.github.yingzhuo.carnival.restful.security.util.JwtTokenFactoryUtils;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @GetMapping("/login")
    public Map<String, Object> login() {
        val token = JwtTokenFactoryUtils.create(
                JwtTokenInfo.builder()
                        .expiresAtFuture(Duration.ofDays(1L))
                        .putPrivateClaim("username", "应卓")
                        .build()
        );
        return Collections.singletonMap("token", token);
    }

    @RequiresAuthentication
    @GetMapping("/access")
    public String access(
            @SecurityContext("#root.userDetails.username") String username
    ) {
        System.out.println("username = " + username);
        return "ok";
    }

}
