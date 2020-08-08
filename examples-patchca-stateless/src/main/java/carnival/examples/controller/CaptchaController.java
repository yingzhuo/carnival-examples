package carnival.examples.controller;

import com.github.yingzhuo.carnival.patchca.CaptchaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CaptchaController {

    @Autowired
    private CaptchaDao captchaDao;

    @GetMapping("/check")
    public String check(@RequestParam("accessKey") String accessKey, @RequestParam("captcha") String captcha) {
        return String.valueOf(
                captchaDao.matches(accessKey, captcha)
        );
    }

}
