package petShop.api.domain.funcionario;


import petShop.api.domain.endereco.Endereco;
import petShop.api.domain.usuario.Usuario;

public record DadosListagemUsuario(
        Long id,
        String nome,
        String telefone,
        String cargo,
        Endereco endereco,
        Boolean ativo,
        String cpf
       ) {


    public DadosListagemUsuario(@org.jetbrains.annotations.NotNull Usuario usuario){
        this(usuario.getId(),
                usuario.getNome(),
                usuario.getTelefone(),
                usuario.getCargo(),
                usuario.getEndereco(),
                usuario.getAtivo(),
                usuario.getCpf()

        );

    }



}
