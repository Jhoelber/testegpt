package petShop.api.domain.agenda.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petShop.api.domain.agenda.Agenda;
import petShop.api.domain.agenda.AgendaRepository;
import petShop.api.domain.agenda.DadosAgendamento;
import petShop.api.domain.agenda.DadosCancelamento;
import petShop.api.domain.animal.Animal;
import petShop.api.domain.servico.Servico;
import petShop.api.domain.animal.AnimalRepository;  // A importação do repositório de Animal
import petShop.api.domain.servico.ServicoRepository; // A importação do repositório de Servico
import petShop.api.domain.veterinario.Veterinario;
import petShop.api.domain.veterinario.VeterinarioRepository;

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

        // Salvar a agenda no banco de dados
        agendaRepository.save(agenda);
    }

    public void cancelar(DadosCancelamento dados) {

    }
}
