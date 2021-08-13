package examples;

import com.github.yingzhuo.carnival.captcha.CaptchaDao;
import com.github.yingzhuo.carnival.captcha.CaptchaHandler;
import com.github.yingzhuo.carnival.captcha.config.CaptchaFilterConfig;
import com.github.yingzhuo.carnival.captcha.config.CaptchaFilterConfigSupplier;
import com.github.yingzhuo.carnival.captcha.handler.DefaultStatelessCaptchaHandler;
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
        return new CaptchaDao() {
            @Override
            public void save(String accessKey, String captcha) {
                // 需要自己实现
            }

            @Override
            public String load(String accessKey) {
                // 需要自己实现
                return null;
            }

            @Override
            public void delete(String accessKey) {
                // 需要自己实现
            }
        };
    }

    @Bean
    CaptchaHandler captchaHandler() {
        return new DefaultStatelessCaptchaHandler();
    }

}
