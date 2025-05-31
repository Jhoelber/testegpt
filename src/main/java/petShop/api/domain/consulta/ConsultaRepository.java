package petShop.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByAnimalIdAndDataBetween(Long idAnimal, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    boolean existsByVeterinarioIdAndDataAndMotivoCancelamentoIsNull(Long idVeterinario, LocalDateTime data);
    boolean existsByAnimalIdAndData(Long idAnimal, LocalDateTime data);

    List<Consulta> findByVeterinarioIdAndDataBetween(Long veterinarioId, LocalDateTime inicio, LocalDateTime fim);

    // ✅ Adicione este:
    @Query("""
        select c.data from Consulta c
        where c.veterinario.id = :veterinarioId
          and date(c.data) = :data
          and c.motivoCancelamento is null
    """)
    List<LocalDateTime> findHorariosOcupados(@Param("veterinarioId") Long veterinarioId,
                                             @Param("data") LocalDate data);
}
