package petShop.api.domain.usuario;


import petShop.api.domain.endereco.Endereco;


public record DadosListagemUsuario(
        Long id,
        String nome,
        String email,
        String telefone,
        String cargo,
        Endereco endereco,
        Boolean ativo,
        String cpf
       ) {


    public DadosListagemUsuario(@org.jetbrains.annotations.NotNull Usuario usuario){
        this(usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getCargo(),
                usuario.getEndereco(),
                usuario.getAtivo(),
                usuario.getCpf()

        );

    }



}
