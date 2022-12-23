package myApp.web_ui.security;

import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Component
public class EncoderAndDecoderPassword {

    public String executeEncode(String password) {
        Base64.Encoder encoder = Base64.getEncoder();
        String encoded = encoder.encodeToString(password.getBytes(StandardCharsets.UTF_8));
        return encoded;
    }

    public String executeDecode(String password) {
        Base64.Decoder decoder = Base64.getDecoder();
        String decoded = new String(decoder.decode(password));
        return decoded;
    }
}