package petShop.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, Long idVeterinario, Long idAnimal, LocalDateTime data) {
    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getVeterinario().getId(), consulta.getAnimal().getId(), consulta.getData());
    }
}
