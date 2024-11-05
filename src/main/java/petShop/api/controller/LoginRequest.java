package petShop.api.controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {

    private String login;
    private String senha;

    // Construtor padrão
    public LoginRequest() {
    }

}
