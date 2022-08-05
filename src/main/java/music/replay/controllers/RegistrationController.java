package music.replay.controllers;

import music.replay.models.RegistrationInfo;
import music.replay.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public void registerUser(@RequestBody RegistrationInfo registrationInfo) {
        registrationService.register(registrationInfo);
    }
}
