package petShop.api.domain.agenda;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import petShop.api.domain.animal.Animal;
import petShop.api.domain.servico.Servico;


import java.time.LocalDateTime;


@Getter
@Setter
@Table(name = "agendas")
@Entity(name = "Agenda")
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    private String status;

    public Agenda(LocalDateTime data, Animal animal, Servico servico, String status) {
        this.data = data;
        this.animal = animal;
        this.servico = servico;
        this.status = status;
    }


    public void setMotivoCancelamento(String motivo) {
    }
}