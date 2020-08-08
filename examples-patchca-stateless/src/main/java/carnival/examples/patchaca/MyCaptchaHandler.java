package carnival.examples.patchaca;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yingzhuo.carnival.patchca.CaptchaHandler;
import com.github.yingzhuo.carnival.patchca.handler.AbstractStatelessCaptchaHandler;
import org.patchca.service.EncodedCaptcha;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component("captchaHandler")
public class MyCaptchaHandler extends AbstractStatelessCaptchaHandler implements CaptchaHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doHandle(EncodedCaptcha captcha, HttpServletRequest request, HttpServletResponse response) throws IOException {

        final Map<String, Object> json = new HashMap<>();
        json.put("accessKey", captcha.getAccessKey());
        json.put("encoded-image", captcha.getEncodeImage());
        json.put("value", captcha.getCaptcha());

        response.setContentType("application/json;charset=utf-8");
        response.getOutputStream().print(objectMapper.writeValueAsString(json));
        response.getOutputStream().flush();
    }

}
