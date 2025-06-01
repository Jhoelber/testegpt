package petShop.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import petShop.api.domain.consulta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = agenda.agendar(dados);

        System.out.println("Dados recebidos:");
        System.out.println("idAnimal: " + dados.idAnimal());
        System.out.println("idVeterinario: " + dados.idVeterinario());
        System.out.println("data: " + dados.data());
        System.out.println("tipo: " + dados.tipo());


        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/disponiveis")
    public ResponseEntity<List<LocalTime>> listarHorariosDisponiveis(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam Long veterinarioId) {

        List<LocalTime> horariosPossiveis = List.of(
                LocalTime.of(8, 0), LocalTime.of(9, 0), LocalTime.of(10, 0),
                LocalTime.of(14, 0), LocalTime.of(15, 0), LocalTime.of(16, 0)
        );

        var ocupados = agenda.getConsultasDoDia(veterinarioId, data);

        List<LocalTime> disponiveis = horariosPossiveis.stream()
                .filter(h -> !ocupados.contains(h))
                .toList();





        return ResponseEntity.ok(disponiveis);
    }

    @GetMapping("/agenda/{idVeterinario}")
    public ResponseEntity<List<AgendaPorDataDTO>> listarAgendaVeterinario(@PathVariable Long idVeterinario) {
        var consultas = repository.findConsultasAgendadasPorVeterinario(idVeterinario);

        var agrupadoPorData = consultas.stream()
                .collect(Collectors.groupingBy(c -> c.data().toLocalDate()))
                .entrySet().stream()
                .map(e -> new AgendaPorDataDTO(e.getKey(), e.getValue()))
                .sorted(Comparator.comparing(AgendaPorDataDTO::data))
                .toList();

        return ResponseEntity.ok(agrupadoPorData);
    }


}
