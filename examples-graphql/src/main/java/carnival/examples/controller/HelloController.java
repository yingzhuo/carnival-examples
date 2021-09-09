package carnival.examples.controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
class HelloController {

    @QueryMapping("hello")
    String hello() {
        return "world";
    }

}
