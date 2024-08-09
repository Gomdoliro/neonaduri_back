package gomdoliro.neonaduri.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaveUserRequest {
    private String email;
    private String password;
}
