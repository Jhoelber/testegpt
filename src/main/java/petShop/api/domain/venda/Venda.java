package petShop.api.domain.venda;

import jakarta.persistence.*;
import lombok.*;
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

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> produto = new ArrayList<>();

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Servico> servico = new ArrayList<>();

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
        this.produto = produto;
        this.servico = servico;
        this.quantidade = quantidade;
        this.dataHora = dataHora;
        this.formaPagamento = formaPagamento;
        this.valorTotal = valorTotal;
        this.tipoVenda = tipoVenda;
    }
}
