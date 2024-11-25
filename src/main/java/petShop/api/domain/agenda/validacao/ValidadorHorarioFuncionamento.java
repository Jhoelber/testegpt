package petShop.api.domain.agenda.validacao;

import org.springframework.stereotype.Component;
import petShop.api.domain.ValidacaoException;

import java.time.LocalDateTime;

@Component
public class ValidadorHorarioFuncionamento {

    public void validar(LocalDateTime dataHora) {
        int hora = dataHora.getHour();

        if (hora < 8 || hora > 18) {
            throw new ValidacaoException("O horário deve estar entre 08:00 e 18:00.");
        }
    }
}

