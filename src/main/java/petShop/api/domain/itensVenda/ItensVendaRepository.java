package petShop.api.domain.itensVenda;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItensVendaRepository extends JpaRepository<ItensVenda, Long> {


    List<ItensVenda> findByVendaId(Long vendaId);


    List<ItensVenda> findByProdutoId(Long produtoId);


    List<ItensVenda> findByServicoId(Long servicoId);
}