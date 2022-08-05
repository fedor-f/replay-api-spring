package music.replay.services;

import music.replay.models.RegistrationInfo;
import music.replay.models.Role;
import music.replay.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserService userService;

    @Autowired
    public RegistrationService(UserService userService) {
        this.userService = userService;
    }

    public void register(RegistrationInfo userInfo) {
        User user = new User(
                userInfo.getUsername(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getPassword(),
                Role.USER
        );
        userService.registerUser(user);
    }
}
