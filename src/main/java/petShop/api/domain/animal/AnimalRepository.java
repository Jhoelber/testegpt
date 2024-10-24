package petShop.api.domain.animal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Page<Animal> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select p.ativo
            from Animal p
            where
            p.id = :id
            """)
    Boolean findAtivoById(Long id);
}
