package carnival.examples.controller;

import com.github.yingzhuo.carnival.mvc.image.Image;
import com.github.yingzhuo.carnival.qrcode.util.QRCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class QRCodeController {

    @GetMapping("/carnival")
    public Image qrcode() {
        return Image.builder()
                .format(Image.Format.PNG)
                .image(QRCodeUtils.generate("https://github.com/yingzhuo/carnival"))
                .build();
    }

}
