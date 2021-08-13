package carnival.examples;

import com.github.yingzhuo.carnival.captcha.CaptchaDao;
import com.github.yingzhuo.carnival.captcha.CaptchaHandler;
import com.github.yingzhuo.carnival.captcha.config.CaptchaFilterConfig;
import com.github.yingzhuo.carnival.captcha.config.CaptchaFilterConfigSupplier;
import com.github.yingzhuo.carnival.captcha.dao.HttpSessionCaptchaDao;
import com.github.yingzhuo.carnival.captcha.handler.DefaultStatefulCaptchaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AppBootCaptcha {

    @Bean
    CaptchaFilterConfigSupplier captchaFilterConfigSupplier() {
        return CaptchaFilterConfig::new;
    }

    @Bean
    CaptchaDao captchaDao() {
        return new HttpSessionCaptchaDao();
    }

    @Bean
    CaptchaHandler captchaHandler() {
        return new DefaultStatefulCaptchaHandler();
    }

}
