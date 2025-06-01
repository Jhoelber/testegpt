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


    @Query("""
        select c.data from Consulta c
        where c.veterinario.id = :veterinarioId
          and date(c.data) = :data
          and c.motivoCancelamento is null
    """)
    List<LocalDateTime> findHorariosOcupados(@Param("veterinarioId") Long veterinarioId,
                                             @Param("data") LocalDate data);

    @Query("""
SELECT new petShop.api.domain.consulta.ConsultaAgendaDTO(
    c.id,
    c.animal.nome,
    c.animal.cliente.nome,
    c.data
)
FROM Consulta c
WHERE c.veterinario.id = :idVeterinario
AND c.motivoCancelamento IS NULL
ORDER BY c.data
""")

    List<ConsultaAgendaDTO> findConsultasAgendadasPorVeterinario(@Param("idVeterinario") Long idVeterinario);

}
