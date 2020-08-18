package carnival.examples.controller;

import com.github.yingzhuo.carnival.password.util.PasswordEncoderUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PasswordEncoderController {

    @PostMapping("/encode-pwd")
    public String encode(@RequestParam("password") String password) {
        return PasswordEncoderUtils.encode(password);
    }

}
