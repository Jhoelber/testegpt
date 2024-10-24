package petShop.api.domain.animal;

import jakarta.validation.constraints.NotNull;
import petShop.api.domain.cliente.DadosCadastroCliente;


public record DadosAtualizacaoAnimal(
        @NotNull
        Long id,
        String nome,
        String especie,
        String vacina,
        String sexo,
        String dataNascimento,
        String cor,
        String descricao,
        String raca,
        String peso,
        DadosCadastroCliente cliente) {

}
