package petShop.api.domain.consulta.validacoes.agendamento;

import petShop.api.domain.ValidacaoException;
import petShop.api.domain.consulta.ConsultaRepository;
import petShop.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAnimalSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var animalPossuiOutraConsultaNoDia = repository.existsByAnimalIdAndDataBetween(dados.idAnimal(), primeiroHorario, ultimoHorario);
        if (animalPossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Animal já possui uma consulta agendada nesse dia");
        }
    }

}
