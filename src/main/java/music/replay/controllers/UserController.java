package music.replay.controllers;

import music.replay.models.Role;
import music.replay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admins")
    public List<String> getAdmins() {
        return userService.getAdmins();
    }

    @GetMapping("/auth-user")
    public String getAuthenticatedUser() {
        return userService.getAuthenticatedUser();
    }
}
