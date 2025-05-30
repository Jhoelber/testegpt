package petShop.api.domain.agenda;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    List<Agenda> findByData(LocalDateTime data);

    @Query("""
    select c.data from Consulta c
    where c.veterinario.id = :veterinarioId
      and DATE(c.data) = :data
      and c.motivoCancelamento is null
""")
    List<LocalDateTime> findHorariosOcupados(@Param("veterinarioId") Long veterinarioId,
                                             @Param("data") LocalDate data);


}