package music.replay.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MvcController {

    @GetMapping("loginPage")
    public String getLoginPage() {
        return "loginPage";
    }

    @GetMapping("signUpPage")
    public String getSignUpPage() {
        return "signUpPage";
    }
}
