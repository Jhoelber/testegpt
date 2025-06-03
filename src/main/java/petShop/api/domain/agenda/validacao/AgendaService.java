package petShop.api.domain.agenda.validacao;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petShop.api.domain.ValidacaoException;
import petShop.api.domain.agenda.*;
import petShop.api.domain.animal.Animal;
import petShop.api.domain.servico.Servico;
import petShop.api.domain.animal.AnimalRepository;  // A importação do repositório de Animal
import petShop.api.domain.servico.ServicoRepository; // A importação do repositório de Servico
import petShop.api.domain.veterinario.Veterinario;
import petShop.api.domain.veterinario.VeterinarioRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgendaService {

    @Autowired
    private List<ValidadorAgendamento> validadores;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public void agendar(DadosAgendamento dados) {
        validadores.forEach(validador -> validador.validar(dados));


        Animal animal = animalRepository.findById(dados.animalId())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));


        Servico servico = servicoRepository.findById(dados.servicoId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        Veterinario veterinario = veterinarioRepository.findById(dados.veterinarioId())
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));

        Agenda agenda = new Agenda(dados.data(), animal, servico, veterinario, "Agendado");


        agendaRepository.save(agenda);
    }



    public void agendarServico(DadosAgendamento dados) {
        validadores.forEach(validador -> validador.validar(dados));

        Animal animal = animalRepository.findById(dados.animalId())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        Servico servico = servicoRepository.findById(dados.servicoId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        Agenda agenda = new Agenda(dados.data(), animal, servico, null, "Agendado");

        agendaRepository.save(agenda);
    }

    public List<HorarioDTO> gerarHorariosDisponiveis(Long servicoId) {
        List<HorarioDTO> horarios = new ArrayList<>();
        LocalDate hoje = LocalDate.now();

        LocalTime[] horariosBase = {
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
                LocalTime.of(16, 0)
        };

        for (int dias = 0; dias < 15; dias++) {
            LocalDate data = hoje.plusDays(dias);

            for (LocalTime hora : horariosBase) {
                LocalDateTime dataHora = LocalDateTime.of(data, hora);

                boolean ocupado = agendaRepository.findByData(dataHora).stream()
                        .anyMatch(agenda ->
                                agenda.getServico().getId().equals(servicoId)
                                        && !agenda.getStatus().equalsIgnoreCase("Cancelado")
                        );
                if (!ocupado) {
                    horarios.add(new HorarioDTO(data.toString(), hora.toString()));
                }
            }
        }

        return horarios;
    }

    public List<AgendaDiaDTO> listarAgendaCompleta() {
        return agendaRepository.findAll().stream()
                .map(a -> new AgendaDiaDTO(
                        a.getData().toLocalDate().toString(),
                        a.getData().toLocalTime().toString(),
                        a.getAnimal().getNome(),
                        a.getServico().getNome()
                ))
                .toList();
    }

    public List<AgendaAnimalDTO> buscarPorAnimal(String nome) {
        return agendaRepository.findAll().stream()
                .filter(a -> a.getAnimal().getNome().toLowerCase().contains(nome.toLowerCase()))
                .map(AgendaAnimalDTO::new)
                .toList();
    }

    @Transactional
    public void cancelar(DadosCancelamento dados) {
        Agenda agendamento = agendaRepository.findById(dados.agendamentoId())
                .orElseThrow(() -> new ValidacaoException("Agendamento não encontrado"));

        agendamento.setStatus("Cancelado");

    }

}
