package petShop.api.domain.consulta.validacoes.agendamento;

import petShop.api.domain.ValidacaoException;
import petShop.api.domain.consulta.DadosAgendamentoConsulta;
import petShop.api.domain.veterinario.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorVeterinarioAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private VeterinarioRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {

        if (dados.idVeterinario() == null) {
            return;
        }

        var veterinarioEstaAtivo = repository.findAtivoById(dados.idVeterinario());
        if (!veterinarioEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com veterinario excluído");
        }
    }

}
