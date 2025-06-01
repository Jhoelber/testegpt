package petShop.api.domain.consulta;

import java.time.LocalDate;
import java.util.List;

public record AgendaPorDataDTO(
        LocalDate data,
        List<ConsultaAgendaDTO> consultas
) {
}
