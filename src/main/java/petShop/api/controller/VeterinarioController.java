package petShop.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import petShop.api.domain.agenda.DadosDisponibilidadeVeterinario;
import petShop.api.domain.agenda.AgendaRepository;
import petShop.api.domain.consulta.ConsultaRepository;
import petShop.api.domain.endereco.Endereco;
import petShop.api.domain.veterinario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import petShop.api.domain.endereco.EnderecoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/veterinarios")
@SecurityRequirement(name = "bearer-key")
public class VeterinarioController {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private VeterinarioRepository repository;

    @Autowired
    private ConsultaRepository consultaRepository;


    @GetMapping("/disponibilidade")
    public ResponseEntity<List<DadosDisponibilidadeVeterinario>> listarDisponibilidade(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {

        Pageable pageable = Pageable.unpaged();
        var page = repository.findAllByAtivoTrue(pageable);
        List<Veterinario> veterinarios = page.getContent();

        // Lista de horários padrão
        List<LocalTime> horariosFixos = List.of(
                LocalTime.of(8, 0), LocalTime.of(9, 0), LocalTime.of(10, 0),
                LocalTime.of(14, 0), LocalTime.of(15, 0), LocalTime.of(16, 0)
        );

        var lista = veterinarios.stream().map(vet -> {
            // Busca horários ocupados no dia, convertendo para LocalTime com minuto e segundo zerados
            var ocupados = consultaRepository.findHorariosOcupados(vet.getId(), data).stream()
                    .map(LocalDateTime::toLocalTime)
                    .toList();

            // Remove da lista os horários que estão ocupados
            var disponiveis = horariosFixos.stream()
                    .filter(h -> !ocupados.contains(h))
                    .toList();

            return new DadosDisponibilidadeVeterinario(
                    vet.getId(),
                    vet.getNome(),
                    disponiveis
            );
        }).toList();

        return ResponseEntity.ok(lista);
    }



    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroVeterinario dados, UriComponentsBuilder uriBuilder) {
        // Criar e definir o Endereco
        Endereco endereco = new Endereco();
        endereco.setLogradouro(dados.endereco().logradouro());
        endereco.setBairro(dados.endereco().bairro());
        endereco.setCep(dados.endereco().cep());
        endereco.setComplemento(dados.endereco().complemento());
        endereco.setNumero(dados.endereco().numero());
        endereco.setUf(dados.endereco().uf());
        endereco.setCidade(dados.endereco().cidade());


        // Salvar o Endereco
        enderecoRepository.save(endereco);

        // Criar o Veterinario usando os dados recebidos
        Veterinario veterinario = new Veterinario(dados);
        veterinario.setEndereco(endereco); // Associar o Endereco ao Veterinário

        // Salvar o Veterinario
        repository.save(veterinario);

        var uri = uriBuilder.path("/veterinarios/{id}").buildAndExpand(veterinario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoVeterinario(veterinario));
    }

    @GetMapping("/{id}/disponibilidade-dias")
    public ResponseEntity<List<LocalDate>> listarDiasComDisponibilidade(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim
    ) {
        List<LocalDate> diasComDisponibilidade = new ArrayList<>();
        List<LocalTime> horariosFixos = List.of(
                LocalTime.of(8, 0), LocalTime.of(9, 0), LocalTime.of(10, 0),
                LocalTime.of(14, 0), LocalTime.of(15, 0), LocalTime.of(16, 0)
        );

        for (LocalDate data = inicio; !data.isAfter(fim); data = data.plusDays(1)) {
            var ocupados = consultaRepository.findHorariosOcupados(id, data).stream()
                    .map(LocalDateTime::toLocalTime)
                    .toList();

            if (ocupados.size() < horariosFixos.size()) {
                diasComDisponibilidade.add(data);
            }
        }

        return ResponseEntity.ok(diasComDisponibilidade);
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemVeterinario>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemVeterinario::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoVeterinario dados) {
        var veterinario = repository.getReferenceById(dados.id());
        veterinario.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoVeterinario(veterinario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var veterinario = repository.getReferenceById(id);
        veterinario.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var veterinario = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoVeterinario(veterinario));
    }

}