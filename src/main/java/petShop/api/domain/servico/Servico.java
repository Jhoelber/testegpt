package petShop.api.domain.servico;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import petShop.api.domain.venda.Venda;

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


}
