package petShop.api.domain.usuario;

import jakarta.validation.constraints.NotNull;
import petShop.api.domain.endereco.DadosEndereco;

public record DadosAtualizarUsuario(
                                    @NotNull
                                    Long id,
                                    String nome,
                                    String email,
                                    String telefone,
                                    String cargo,
                                    String ativo,
                                    String cpf,
                                    DadosEndereco endereco

) {

}
