package petShop.api.domain.consulta;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public record ConsultaAgendaDTO(
        Long id,
        String nomeAnimal,
        String nomeCliente,
        String data
) {
    public ConsultaAgendaDTO(Consulta consulta) {
        this(
                consulta.getId(),
                consulta.getAnimal().getNome(),
                consulta.getAnimal().getCliente().getNome(),
                consulta.getData()
                        .atZone(ZoneId.of("America/Sao_Paulo"))
                        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        );
    }
}
