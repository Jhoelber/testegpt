package petShop.api.domain.venda;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.parameters.P;
import petShop.api.domain.cliente.Cliente;
import petShop.api.domain.funcionario.Funcionario;
import petShop.api.domain.produto.Produto;
import petShop.api.domain.servico.Servico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "vendas")
@Entity(name = "Venda")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
@NoArgsConstructor
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @ManyToMany
    @JoinTable(
            name = "venda_produto",
            joinColumns = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;

    @ManyToMany
    @JoinTable(
            name = "venda_servico",
            joinColumns = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private List<Servico> servicos;

    private int quantidade;
    private LocalDateTime dataHora;
    private String formaPagamento;
    private double valorTotal;
    @Enumerated(EnumType.STRING)
    private TipoVenda tipoVenda;


    public Venda(Cliente cliente,
                 Funcionario funcionario,
                 List<Produto> produto,
                 List<Servico> servico,
                 int quantidade,
                 LocalDateTime dataHora,
                 String formaPagamento,
                 double valorTotal,
                 TipoVenda tipoVenda) {

        this.cliente = cliente;
        this.funcionario = funcionario;
        this.produtos = produto;
        this.servicos = servico;
        this.quantidade = quantidade;
        this.dataHora = dataHora;
        this.formaPagamento = formaPagamento;
        this.valorTotal = valorTotal;
        this.tipoVenda = tipoVenda;
    }
}
