package petShop.api.domain.venda;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DadosVenda {

    @NotNull
    private Long clienteId;

    @NotNull
    private Long funcionarioId;

    private Long produtoId;
    private Long servicoId;

    @NotNull
    private int quantidade;

    @NotNull
    private String formaPagamento;

    @NotNull
    private double valorTotal;

    private TipoVenda tipoVenda;


    public DadosVenda(Long clienteId, Long funcionarioId, Long produtoId, Long servicoId, int quantidade, String formaPagamento, double valorTotal, TipoVenda tipoVenda) {
        this.clienteId = clienteId;
        this.funcionarioId = funcionarioId;
        this.produtoId = produtoId;
        this.servicoId = servicoId;
        this.quantidade = quantidade;
        this.formaPagamento = formaPagamento;
        this.valorTotal = valorTotal;
        this.tipoVenda = tipoVenda;
    }
}
