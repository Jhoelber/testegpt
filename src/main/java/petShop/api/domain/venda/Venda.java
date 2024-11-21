package petShop.api.domain.venda;

import jakarta.persistence.*;
import lombok.*;
import petShop.api.domain.cliente.Cliente;

import petShop.api.domain.itensVenda.ItensVenda;
import petShop.api.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "vendas")
@Entity(name = "Venda")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
    private Usuario usuario;

    private int quantidade;
    private LocalDateTime dataHora;
    private String formaPagamento;
    private double valorTotal;

    @Enumerated(EnumType.STRING)
    private TipoVenda tipoVenda;


    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItensVenda> itensVenda;

    public Venda(Cliente cliente,
                 Usuario usuario,
                 int quantidade,
                 LocalDateTime dataHora,
                 String formaPagamento,
                 double valorTotal,
                 TipoVenda tipoVenda,
                 List<ItensVenda> itensVenda) {

        this.cliente = cliente;
        this.usuario = usuario;
        this.quantidade = quantidade;
        this.dataHora = dataHora;
        this.formaPagamento = formaPagamento;
        this.valorTotal = valorTotal;
        this.tipoVenda = tipoVenda;
        this.itensVenda = itensVenda;
    }
}
