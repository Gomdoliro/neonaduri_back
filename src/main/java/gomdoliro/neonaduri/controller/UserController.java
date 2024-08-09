package gomdoliro.neonaduri.controller;

import gomdoliro.neonaduri.controller.dto.SaveUserRequest;
import gomdoliro.neonaduri.controller.dto.SaveUserResponse;
import gomdoliro.neonaduri.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public SaveUserResponse signup(@RequestBody SaveUserRequest request) {
        return userService.signup(request);
    }
}
