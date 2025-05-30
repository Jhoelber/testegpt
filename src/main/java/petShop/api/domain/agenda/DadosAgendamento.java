package petShop.api.domain.agenda;


import java.time.LocalDateTime;

public record DadosAgendamento(
        Long servicoId,
        Long animalId,
        Long veterinarioId,
        String status,
        LocalDateTime data
) {


}
