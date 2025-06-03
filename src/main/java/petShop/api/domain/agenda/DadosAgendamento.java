package petShop.api.domain.agenda;


import java.time.LocalDateTime;

public record DadosAgendamento(
        LocalDateTime data,
        Long servicoId,
        Long animalId,
        Long veterinarioId // pode deixar opcional nesse caso
) {}

