package petShop.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByAnimalIdAndDataBetween(Long idAnimal, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    boolean existsByVeterinarioIdAndDataAndMotivoCancelamentoIsNull(Long idVeterinario, LocalDateTime data);

    List<Consulta> findByVeterinarioIdAndDataBetween(Long veterinarioId, LocalDateTime inicio, LocalDateTime fim);

}
