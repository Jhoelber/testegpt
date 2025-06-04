package petShop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petShop.api.domain.agenda.*;
import petShop.api.domain.agenda.validacao.AgendaService;
import petShop.api.domain.agenda.validacao.ValidadorAgendamento;
import petShop.api.domain.agenda.validacao.ValidadorHorarioFuncionamento;
import petShop.api.domain.animal.Animal;
import petShop.api.domain.animal.AnimalRepository;
import petShop.api.domain.servico.Servico;
import petShop.api.domain.servico.ServicoRepository;
import petShop.api.domain.veterinario.Veterinario;
import petShop.api.domain.veterinario.VeterinarioRepository;

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
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private AgendaService agendaService;
    @Autowired
    private ValidadorAgendamento validadorAgendamento;
    @Autowired
    private ValidadorHorarioFuncionamento validadorHorarioFuncionamento;

    @GetMapping
    public List<Agenda> listarTodas() {
        return agendaRepository.findAll();
    }


    @GetMapping("/por-animal")
    public ResponseEntity<List<AgendaAnimalDTO>> buscarPorAnimal(@RequestParam String nome) {
        List<AgendaAnimalDTO> resultado = agendaService.buscarPorAnimal(nome);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/todas-por-dia")
    public ResponseEntity<List<AgendaDiaDTO>> listarAgendaCompleta() {
        return ResponseEntity.ok(agendaService.listarAgendaCompleta());
    }

    @GetMapping("/horarios-disponiveis/{servicoId}")
    public ResponseEntity<List<HorarioDTO>> listarHorariosDisponiveis(@PathVariable Long servicoId) {
        return ResponseEntity.ok(agendaService.gerarHorariosDisponiveis(servicoId));
    }

    @GetMapping("/disponibilidade")
    public ResponseEntity<Boolean> verificarDisponibilidade(
            @RequestParam LocalDateTime data,
            @RequestParam Long servicoId) {

        boolean disponivel = agendaRepository.findByData(data).stream()
                .noneMatch(agenda -> agenda.getServico().getId().equals(servicoId));

        return ResponseEntity.ok(disponivel);
    }

    @PostMapping("/agendar")
    public ResponseEntity<?> agendar(@RequestBody DadosAgendamento dados) {
        validadorHorarioFuncionamento.validar(dados.data());

        if (dados.servicoId() == null || dados.animalId() == null) {
            return ResponseEntity.badRequest().body("Serviço ou Animal ID não podem ser nulos");
        }

        validadorAgendamento.validar(dados);

        Servico servico = servicoRepository.findById(dados.servicoId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
        Animal animal = animalRepository.findById(dados.animalId())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
        Veterinario veterinario = veterinarioRepository.findById(dados.veterinarioId())
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));

        Agenda novoAgendamento = new Agenda(dados.data(), animal, servico, veterinario, "Agendado");
        Agenda agendamentoSalvo = agendaRepository.save(novoAgendamento);

        return ResponseEntity.status(201).body(agendamentoSalvo);
    }

    @PostMapping("/agendar-servico")
    public ResponseEntity<?> agendarServico(@RequestBody DadosAgendamento dados) {
        validadorHorarioFuncionamento.validar(dados.data());

        if (dados.servicoId() == null || dados.animalId() == null) {
            return ResponseEntity.badRequest().body("Serviço ou Animal ID não podem ser nulos");
        }

        agendaService.agendarServico(dados);
        return ResponseEntity.status(201).build();
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
                    return ResponseEntity.ok(agendaRepository.save(agenda));
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


    @PostMapping("/cancelar")
    public ResponseEntity<?> cancelar(@RequestBody DadosCancelamento dados) {
        agendaService.cancelar(dados); // ← aqui que o id pode estar nulo
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/por-dia")
    public ResponseEntity<List<AgendaDiaDTO>> buscarPorDia(@RequestParam LocalDate data) {
        List<AgendaDiaDTO> agendamentos = agendaService.buscarPorDia(data);
        return ResponseEntity.ok(agendamentos);
    }



}
