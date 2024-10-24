package petShop.api.domain.produto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarProduto(
        @NotNull
        Long id,
        String nome,
        String categoria,
        String quantidade,
        String valorVenda,
        String valorCompra,
        String unidadeDeMedida,
        String codigo,
        String minEstoque



) {

}
