package petShop.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import petShop.api.domain.veterinario.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idVeterinario,

        @NotNull
        Long idAnimal,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade) {
}
