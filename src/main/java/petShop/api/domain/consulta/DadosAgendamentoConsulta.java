package petShop.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import petShop.api.domain.veterinario.Especialidade;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        @NotNull
        Long idVeterinario,

        @NotNull
        Long idAnimal,

        @NotNull
        @Future
        LocalDateTime data,

        @NotNull
        TipoConsulta tipo,

        BigDecimal valor,

        Especialidade especialidade



) {

}
