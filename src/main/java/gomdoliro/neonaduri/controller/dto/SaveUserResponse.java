package gomdoliro.neonaduri.controller.dto;

import gomdoliro.neonaduri.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveUserResponse {

    private Long userid;

    private String email;

    private String password;

    public SaveUserResponse(User user) {
        this.userid = user.getUserid();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
