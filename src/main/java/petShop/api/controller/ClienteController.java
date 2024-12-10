package petShop.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import petShop.api.domain.cliente.*;
import petShop.api.domain.endereco.DadosEndereco;
import petShop.api.domain.endereco.Endereco;
import petShop.api.domain.endereco.EnderecoRepository;

@RestController
@CrossOrigin(origins = "http://localhost:5173/TelaAnimais")
@RequestMapping("clientes")
@SecurityRequirement(name = "bearer-key")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;
    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {

        DadosEndereco dadosEndereco = dados.endereco();
        Endereco endereco = new Endereco(
                null, // id será gerado automaticamente pelo banco de dados
                ((DadosEndereco) dadosEndereco).logradouro(),
                dadosEndereco.bairro(),
                dadosEndereco.cep(),
                dadosEndereco.numero(),
                dadosEndereco.complemento(),
                dadosEndereco.cidade(),
                dadosEndereco.uf()
        );


        enderecoRepository.save(endereco);


        Cliente cliente = new Cliente(dados);
        cliente.setEndereco(endereco);

        // Salva o cliente
        repository.save(cliente);

        // Retorna a resposta com a URL do novo recurso
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));
    }



    @GetMapping
    public ResponseEntity<Page<DadosListagemCliente>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados) {
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var cliente = repository.getReferenceById(id);
        cliente.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var cliente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }
}
