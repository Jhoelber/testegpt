package petShop.api.domain.servico;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import petShop.api.domain.agenda.Agenda;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Table(name = "servicos")
@Entity(name = "Servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private double preco;
    private String descricao;

    @OneToMany(mappedBy = "servico")
    private List<Agenda> agendas;
}
