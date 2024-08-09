package gomdoliro.neonaduri.service;

import gomdoliro.neonaduri.controller.dto.SaveUserRequest;
import gomdoliro.neonaduri.controller.dto.SaveUserResponse;
import gomdoliro.neonaduri.domain.User;
import gomdoliro.neonaduri.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SaveUserResponse signup(SaveUserRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        User save = userRepository.save(user);
        return new SaveUserResponse(save);
    }
}
