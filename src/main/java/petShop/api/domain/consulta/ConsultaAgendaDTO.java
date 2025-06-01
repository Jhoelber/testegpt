package petShop.api.domain.consulta;

import java.time.LocalDateTime;

public record ConsultaAgendaDTO(
        Long id,
        String nomeAnimal,
        String nomeCliente,
        LocalDateTime data
) {}