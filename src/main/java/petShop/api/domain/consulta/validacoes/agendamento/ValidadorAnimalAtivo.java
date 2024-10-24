package petShop.api.domain.consulta.validacoes.agendamento;

import petShop.api.domain.ValidacaoException;
import petShop.api.domain.consulta.DadosAgendamentoConsulta;
import petShop.api.domain.animal.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAnimalAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private AnimalRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var animalEstaAtivo = repository.findAtivoById(dados.idAnimal());
        if (!animalEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com animal excluído");
        }
    }
}
