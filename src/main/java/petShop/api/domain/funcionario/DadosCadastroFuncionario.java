package petShop.api.domain.funcionario;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import petShop.api.domain.endereco.DadosEndereco;

public record DadosCadastroFuncionario(
        @NotBlank
        String nome,
        @NotBlank
        String telefone,
        @NotBlank
        String cargo,

        @NotBlank
        String email,
        @NotNull
        @Valid
        DadosEndereco endereco
        ) {

}



