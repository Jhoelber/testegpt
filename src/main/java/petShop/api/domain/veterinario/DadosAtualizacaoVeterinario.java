package petShop.api.domain.veterinario;

import jakarta.validation.constraints.NotNull;
import petShop.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoVeterinario(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
