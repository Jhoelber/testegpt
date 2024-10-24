package petShop.api.domain.funcionario;


import jakarta.validation.constraints.NotNull;
import petShop.api.domain.endereco.DadosEndereco;

public record DadosAtualizarFuncionario(
      @NotNull
      Long id,
      String nome,
      String telefone,
      String cargo,
      String ativo,
      DadosEndereco endereco,
      String email


) {

}
