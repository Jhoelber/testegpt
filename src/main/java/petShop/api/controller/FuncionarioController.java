package petShop.api.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import petShop.api.domain.funcionario.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroFuncionario dados) {
        repository.save(new Funcionario(dados));

    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping
    public Page<DadosListagemFuncionario> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemFuncionario::new);
    }

    @PutMapping
    @Transactional
    @CrossOrigin(origins = "http://localhost:5173")
    public void atualizar(@RequestBody @Valid DadosAtualizarFuncionario dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

    }

    @PutMapping("/{id}")
    @Transactional
    @CrossOrigin(origins = "http://localhost:5173")
    public void atualizar(@PathVariable long id, @RequestBody @Valid DadosAtualizarFuncionario dados) {
        var funcionario = repository.getReferenceById(id);
        funcionario.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CrossOrigin(origins = "http://localhost:5173")
    public void excluir(@PathVariable long id){
        var funcionario = repository.getReferenceById(id);
        funcionario.excluir();
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:5173")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowCredentials(true);
        }
    }
}