package petShop.api.domain.funcionario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import petShop.api.domain.endereco.Endereco;


@Table(name = "funcionario")
@Entity(name = "Funcionario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String cargo;
    private String email;

    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Funcionario(DadosCadastroFuncionario dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.cargo = dados.cargo();
        this.email = dados.email();
        this.endereco = new Endereco(dados.endereco());


    }

    public void atualizarInformacoes(DadosAtualizarFuncionario dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }

        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }

        if(dados.cargo() != null){
            this.cargo = dados.cargo();
        }

        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }

        if(dados.email() != null){
            this.email = dados.email();
        }

    }
    public void excluir() {
        this.ativo = false;
    }
}