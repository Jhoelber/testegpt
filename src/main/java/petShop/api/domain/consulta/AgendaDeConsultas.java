package petShop.api.domain.consulta;

import petShop.api.domain.ValidacaoException;
import petShop.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import petShop.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;

import petShop.api.domain.veterinario.Veterinario;
import petShop.api.domain.veterinario.VeterinarioRepository;
import petShop.api.domain.animal.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!animalRepository.existsById(dados.idAnimal())) {
            throw new ValidacaoException("Id do animal informado não existe!");
        }

        if (dados.idVeterinario() != null && !veterinarioRepository.existsById(dados.idVeterinario())) {
            throw new ValidacaoException("Id do Veterinário informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var animal = animalRepository.getReferenceById(dados.idAnimal());
        var veterinario = escolherVeterinario(dados);
        if (veterinario == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data!");
        }

        // Define valor com base no tipo
        BigDecimal valor;
        switch (dados.tipo()) {
            case CONSULTA -> valor = new BigDecimal("100.00");
            case RETORNO -> valor = BigDecimal.ZERO;
            case VACINACAO -> valor = null; // definido presencialmente
            default -> throw new IllegalArgumentException("Tipo de consulta inválido");
        }

        var consulta = new Consulta(null, veterinario, animal, dados.data(), valor, dados.tipo());
        consultaRepository.save(consulta);

        consulta.setValor(valor);
        consulta.setTipo(dados.tipo());





        return new DadosDetalhamentoConsulta(consulta);
    }


    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }


    private Veterinario escolherVeterinario(DadosAgendamentoConsulta dados) {
        if (dados.idVeterinario() != null) {
            return veterinarioRepository.getReferenceById(dados.idVeterinario());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando Veterinario não for escolhido!");
        }

        return veterinarioRepository.escolherVeterinarioAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public List<LocalTime> getConsultasDoDia(Long veterinarioId, LocalDate data) {
        var consultas = consultaRepository.findByVeterinarioIdAndDataBetween(
                veterinarioId,
                data.atStartOfDay(),
                data.atTime(23, 59)
        );

        return consultas.stream()
                .map(c -> c.getData().toLocalTime())
                .toList();
    }


}
