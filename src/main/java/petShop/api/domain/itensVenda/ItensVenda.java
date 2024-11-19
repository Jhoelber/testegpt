package petShop.api.domain.itensVenda;

import jakarta.persistence.*;
import lombok.*;
import petShop.api.domain.produto.Produto;
import petShop.api.domain.servico.Servico;
import petShop.api.domain.venda.Venda;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_venda")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItensVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = true)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = true)
    private Servico servico;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "valor_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorUnitario;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;
}
