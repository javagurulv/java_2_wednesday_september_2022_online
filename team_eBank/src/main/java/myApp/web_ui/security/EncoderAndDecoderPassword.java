package myApp.web_ui.security;

import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Component
public class EncoderAndDecoderPassword {

    public String executeEncode(String password) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(password.getBytes(StandardCharsets.UTF_8));
    }

    public String executeDecode(String password) {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(password));
    }
}