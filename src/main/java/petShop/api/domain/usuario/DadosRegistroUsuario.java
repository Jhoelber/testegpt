package petShop.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import petShop.api.domain.endereco.Endereco;

public record DadosRegistroUsuario(
        @NotBlank(message = "Email é obrigatório") String email,
        @NotBlank(message = "Senha é obrigatória") String senha,
        @NotBlank(message = "Role é obrigatória") String role,
        @NotBlank(message = "Nome é obrigatório") String nome,
        @NotBlank(message = "Telefone é obrigatório") String telefone,
        @NotBlank(message = "Cargo é obrigatório") String cargo,

        @NotBlank(message = "CPF é obrigatório") String cpf,
        String crm,
        Endereco endereco
) {
}
