package petShop.api.domain.produto;

public record DadosListagemProduto(Long id,
                                   String nome,
                                   String categoria,
                                   String quantidade,
                                   int valorCompra,
                                   int valorVenda,
                                   String codigo,
                                   UnidadeDeMedida unidadeDeMedida,
                                   String minEstoque ) {

public DadosListagemProduto(@org.jetbrains.annotations.NotNull Produto cliente){
    this(cliente.getId(),
            cliente.getNome(),
            cliente.getCategoria(),
            cliente.getQuantidade(),
            cliente.getValorCompra(),
            cliente.getValorVenda(),
            cliente.getCodigo(),
            cliente.getUnidadeDeMedida(),
            cliente.getMinEstoque()

            );
}
}