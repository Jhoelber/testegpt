package petShop.api.domain.servico;

public record DadosListagemServico(Long id, String nome, String descricao, Double preco) {
    public DadosListagemServico(Servico servico) {
        this(servico.getId(), servico.getNome(), servico.getDescricao(), servico.getPreco());
    }


}
