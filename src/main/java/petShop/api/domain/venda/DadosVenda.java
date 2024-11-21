package petShop.api.domain.venda;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DadosVenda {

    @NotNull
    private Long clienteId;

    @NotNull
    private Long usuarioId;

    private List<Long> produtoIds;
    private List<Long> servicoIds;

    @NotNull
    private int quantidade;

    @NotNull
    private String formaPagamento;

    @NotNull
    private double valorTotal;

    private TipoVenda tipoVenda;


    public DadosVenda(Long clienteId, Long usuarioId, List<Long> produtoIds, List<Long> servicoIds, int quantidade, String formaPagamento, double valorTotal, TipoVenda tipoVenda) {
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.produtoIds = produtoIds;
        this.servicoIds = servicoIds;
        this.quantidade = quantidade;
        this.formaPagamento = formaPagamento;
        this.valorTotal = valorTotal;
        this.tipoVenda = tipoVenda;
    }


}
