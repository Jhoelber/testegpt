package petShop.api.domain.veterinario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    Page<Veterinario> findAllByAtivoTrue(Pageable paginacao);


    @Query("""
            select v from Veterinario v
            where
            v.ativo = true
            and
            v.especialidade = :especialidade
            and
            v.id not in(
                select c.veterinario.id from Consulta c
                where
                c.data = :data
                and
                c.motivoCancelamento is null
            )
            order by rand()
            limit 1
        """)
    Veterinario escolherVeterinarioAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query("""
            select v.ativo
            from Veterinario v
            where
            v.id = :id
            """)
    Boolean findAtivoById(Long id);
}
