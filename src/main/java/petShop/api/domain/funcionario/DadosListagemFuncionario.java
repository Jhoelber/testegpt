package petShop.api.domain.funcionario;


import petShop.api.domain.endereco.Endereco;

public record DadosListagemFuncionario(
        Long id,
        String nome,
        String telefone,
        String cargo,
        Endereco endereco,
        Boolean ativo,
        String email) {


    public DadosListagemFuncionario(@org.jetbrains.annotations.NotNull Funcionario funcionario){
        this(funcionario.getId(),
                        funcionario.getNome(),
                        funcionario.getTelefone(),
                        funcionario.getCargo(),
                funcionario.getEndereco(),
                funcionario.getAtivo(),
                funcionario.getEmail()
                );

    }



}
