package petShop.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosRegistroUsuario(
        @NotBlank(message = "Login é obrigatório") String login,
        @NotBlank(message = "Senha é obrigatória") String senha,
        @NotBlank(message = "Role é obrigatória") String role) {
}
