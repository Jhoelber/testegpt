package petShop.api.domain.cliente;

import jakarta.validation.constraints.NotNull;
import petShop.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoCliente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
