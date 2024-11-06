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
import petShop.api.domain.produto.DadosAtualizarProduto;
import petShop.api.domain.produto.DadosCadastroProduto;
import petShop.api.domain.produto.DadosListagemProduto;
import petShop.api.domain.produto.Produto;
import petShop.api.domain.produto.ProdutoRepository;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;


    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroProduto dados) {
        repository.save(new Produto(dados));
    }


    @GetMapping
    public Page<DadosListagemProduto> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

       return repository.findAllByAtivoTrue(paginacao).map(DadosListagemProduto::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarProduto dados){
    var produto = repository.getReferenceById(dados.id());
        produto.atualizarInformacoes(dados);

    }

   @PutMapping("/{id}")
    @Transactional
    public void atualizar(@PathVariable long id, @RequestBody @Valid DadosAtualizarProduto dados) {
        var produto = repository.getReferenceById(id);
        produto.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional

    public void excluir(@PathVariable long id){
        var produto = repository.getReferenceById(id);
        produto.excluir();
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