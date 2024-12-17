package petShop.api.domain.cliente;

import petShop.api.domain.endereco.Endereco;

public record DadosListagemCliente(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        Endereco endereco


) {

    public DadosListagemCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCpf(), cliente.getTelefone(), cliente.getEndereco());
    }

}
