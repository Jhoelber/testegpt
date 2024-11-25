package petShop.api.domain.agenda.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import petShop.api.domain.agenda.AgendaRepository;
import petShop.api.domain.ValidacaoException;


import java.time.LocalDateTime;

@Component
public class ValidadorDataDisponivel {

    @Autowired
    private AgendaRepository agendaRepository;

    public void validar(LocalDateTime data, Long servicoId) {
        boolean disponivel = agendaRepository.findByData(data).stream()
                .noneMatch(agenda -> agenda.getServico().getId().equals(servicoId));

        if (!disponivel) {
            throw new ValidacaoException("Já existe um agendamento para esse serviço na data informada.");
        }
    }
}
