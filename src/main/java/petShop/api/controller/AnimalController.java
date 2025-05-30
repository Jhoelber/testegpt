package petShop.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import petShop.api.domain.animal.*;
import petShop.api.domain.cliente.Cliente;
import petShop.api.domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import petShop.api.domain.animal.AnimalRepository;
import petShop.api.domain.animal.AnimalService;
@RestController
@RequestMapping("animais")
public class AnimalController {

    @Autowired
    private AnimalRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AnimalService animalService;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody DadosCadastroAnimal dados, UriComponentsBuilder uriBuilder) {
        Cliente cliente = clienteRepository.findById(dados.clientesId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        if (animalService.existeAnimalComMesmoNomeESpecieECliente(dados.nome(), dados.especie(), dados.clientesId())) {
            return ResponseEntity.badRequest().body("Animal já cadastrado");
        }

        Animal animal = new Animal(dados);
        animal.setCliente(cliente);
        repository.save(animal);
        var uri = uriBuilder.path("/animais/{id}").buildAndExpand(animal.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoAnimal(animal));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAnimal>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemAnimal::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoAnimal dados) {
        var animal = repository.getReferenceById(dados.id());
        animal.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoAnimal(animal));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var animal = repository.getReferenceById(id);
        animal.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var animal = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoAnimal(animal));
    }
}