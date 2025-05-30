package petShop.api.domain.agenda;

import java.time.LocalTime;
import java.util.List;

public record DadosDisponibilidadeVeterinario(
        Long id,
        String nome,
        List<LocalTime> horariosDisponiveis
) {}
