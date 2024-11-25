package petShop.api.domain.agenda.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import petShop.api.domain.agenda.AgendaRepository;
import petShop.api.domain.agenda.DadosAgendamento;
import petShop.api.domain.animal.AnimalRepository;


@Component
public class ValidadorAgendamento {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private AnimalRepository animalRepository;


    @Autowired
    private ValidadorHorarioFuncionamento validadorHorario;

    @Autowired
    private ValidadorDataDisponivel validadorDataDisponivel;

    public void validar(DadosAgendamento dados) {


        validadorDataDisponivel.validar(dados.data(), dados.servicoId());


        var animal = animalRepository.findById(dados.animalId())
                .orElseThrow(() -> new IllegalArgumentException("Animal não encontrado."));

        if (!animal.isAtivo()) {
            throw new IllegalArgumentException("O animal não está ativo.");
        }




        validadorHorario.validar(dados.data());
    }
}
