package petShop.api.domain.animal;

import jakarta.validation.constraints.NotNull;
import petShop.api.domain.cliente.DadosCadastroCliente;

import java.time.LocalDate;


public record DadosAtualizacaoAnimal(
        @NotNull
        Long id,
        String nome,
        String especie,
        String vacina,
        String sexo,
        LocalDate dataNascimento,
        String cor,
        String descricao,
        String raca,
        String peso,
        String foto,
        DadosCadastroCliente cliente) {

}
