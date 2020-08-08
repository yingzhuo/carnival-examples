package carnival.examples.controller;

import com.github.yingzhuo.carnival.patchca.CaptchaDao;
import com.github.yingzhuo.carnival.patchca.SessionPatchca;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CaptchaController {

    @Autowired
    private CaptchaDao captchaDao;

    @GetMapping("/check")
    public String check(@RequestParam("captcha") String captcha, @SessionPatchca String sessionCaptcha) {
        return String.valueOf(
                StringUtils.equals(captcha, sessionCaptcha)
        );
    }

}
