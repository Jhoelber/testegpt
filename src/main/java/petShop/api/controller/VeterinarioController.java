package petShop.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
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

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("veterinarios")
@SecurityRequirement(name = "bearer-key")
public class VeterinarioController {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private VeterinarioRepository repository;

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