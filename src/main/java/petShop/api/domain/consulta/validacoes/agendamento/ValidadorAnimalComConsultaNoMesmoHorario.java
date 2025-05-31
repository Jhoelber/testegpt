package petShop.api.domain.consulta.validacoes.agendamento;

import petShop.api.domain.ValidacaoException;
import petShop.api.domain.consulta.ConsultaRepository;
import petShop.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAnimalComConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var animalJaTemConsultaNoHorario = repository.existsByAnimalIdAndData(dados.idAnimal(), dados.data());
        if (animalJaTemConsultaNoHorario) {
            throw new ValidacaoException("Animal já possui uma consulta neste horário.");
        }
    }
}
