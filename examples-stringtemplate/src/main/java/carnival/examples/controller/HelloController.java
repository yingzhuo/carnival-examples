package carnival.examples.controller;

import com.github.yingzhuo.carnival.common.io.ResourceText;
import com.github.yingzhuo.carnival.stringtemplate.utils.StringTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
class HelloController {

    private ResourceText text = ResourceText.of("classpath:/config/hello.txt");

    @GetMapping("/hello")
    public Map<String, Object> hello() {

        Map<String, Object> context = new HashMap<>();
        context.put("world", "world");

        return Collections.singletonMap("result", StringTemplateUtils.fromString(text.getText(), context));
    }

}
