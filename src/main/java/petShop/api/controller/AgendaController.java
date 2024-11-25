package petShop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petShop.api.domain.agenda.Agenda;
import petShop.api.domain.agenda.AgendaRepository;
import petShop.api.domain.agenda.DadosAgendamento;
import petShop.api.domain.agenda.validacao.ValidadorAgendamento;
import petShop.api.domain.agenda.validacao.ValidadorHorarioFuncionamento;
import petShop.api.domain.animal.Animal;
import petShop.api.domain.animal.AnimalRepository;
import petShop.api.domain.servico.Servico;
import petShop.api.domain.servico.ServicoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agendas")
public class AgendaController {
    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private ValidadorAgendamento validadorAgendamento;
    @Autowired
    private ValidadorHorarioFuncionamento validadorHorarioFuncionamento;
    @GetMapping
    public List<Agenda> listarTodas() {
        return agendaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agenda> buscarPorId(@PathVariable Long id) {
        return agendaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Agenda criar(@RequestBody Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agenda> atualizar(@PathVariable Long id, @RequestBody Agenda agendaAtualizada) {
        return agendaRepository.findById(id)
                .map(agenda -> {
                    agenda.setData(agendaAtualizada.getData());
                    agenda.setAnimal(agendaAtualizada.getAnimal());
                    agenda.setServico(agendaAtualizada.getServico());
                    agenda.setStatus(agendaAtualizada.getStatus());
                    Agenda atualizado = agendaRepository.save(agenda);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return agendaRepository.findById(id)
                .map(agenda -> {
                    agendaRepository.delete(agenda);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/disponibilidade")
    public ResponseEntity<Boolean> verificarDisponibilidade(@RequestParam LocalDateTime data, @RequestParam Long servicoId) {
        boolean disponivel = agendaRepository.findByData(data).stream()
                .noneMatch(agenda -> agenda.getServico().getId().equals(servicoId));
        return ResponseEntity.ok(disponivel);
    }

    @PostMapping("/agendar")
    public ResponseEntity<?> agendar(@RequestBody DadosAgendamento dados) {

        // Validar o horário de agendamento com base no campo data
        validadorHorarioFuncionamento.validar(dados.data());  // Validar horário antes de prosseguir

        // Validar se os dados de animalId e servicoId são válidos
        if (dados.servicoId() == null || dados.animalId() == null) {
            return ResponseEntity.badRequest().body("Serviço ou Animal ID não podem ser nulos");
        }

        // Validar os dados de agendamento (como você já fazia)
        validadorAgendamento.validar(dados);

        // Obter o serviço e o animal do banco de dados
        Servico servico = servicoRepository.findById(dados.servicoId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        Animal animal = animalRepository.findById(dados.animalId())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        // Criar o agendamento
        Agenda novoAgendamento = new Agenda(dados.data(), animal, servico, "Agendado");
        novoAgendamento.setData(dados.data());
        novoAgendamento.setServico(servico);
        novoAgendamento.setAnimal(animal);
        novoAgendamento.setStatus("Agendado");

        // Salvar o agendamento
        Agenda agendamentoSalvo = agendaRepository.save(novoAgendamento);

        return ResponseEntity.status(201).body(agendamentoSalvo);
    }


}
