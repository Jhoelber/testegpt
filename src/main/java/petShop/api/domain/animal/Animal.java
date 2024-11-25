package petShop.api.domain.animal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import petShop.api.domain.agenda.Agenda;
import petShop.api.domain.cliente.Cliente;

import java.time.LocalDate;
import java.util.List;


@Table(name = "animais")
@Entity(name = "Animal")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Animal {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String especie;
    private String vacina;
    private String sexo;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    private String cor;
    private String descricao;
    private String raca;
    private String peso;

    @OneToMany(mappedBy = "animal")
    @JsonIgnore
    private List<Agenda> agendas;
    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientes_id")
    private Cliente cliente;

    private Boolean ativo;

    public Animal(DadosCadastroAnimal dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.especie= dados.especie();
        this.vacina = dados.vacina();
        this.sexo = dados.sexo();
        this.dataNascimento = dados.dataNascimento();
        this.cor = dados.cor();
        this.descricao = dados.descricao();
        this.raca = dados.raca();
        this.peso = dados.peso();
    }



    public void atualizarInformacoes(DadosAtualizacaoAnimal dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.especie() != null) {
            this.especie = dados.especie();
        }
        if (dados.vacina() != null) {
            this.vacina = dados.vacina();
        }
        if (dados.sexo() != null) {
            this.sexo = dados.sexo();
        }
        if (dados.dataNascimento() != null) {
            this.dataNascimento = dados.dataNascimento();
        } if (dados.cor() != null) {
            this.cor = dados.cor();
        } if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        } if (dados.raca() != null) {
            this.raca = dados.raca();
        }
        if (dados.peso() != null) {
            this.peso = dados.peso();
        }

        if (dados.cliente() != null) {

            this.cliente.atualizarInformacoes(dados.cliente());
        }

    }

    public void excluir() {
        this.ativo = false;
    }

    public Boolean isAtivo() {
        return ativo;
    }


}
