package petShop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petShop.api.domain.servico.Servico;
import petShop.api.domain.servico.ServicoService;

import java.util.List;

@RestController
@RequestMapping("servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public ResponseEntity<List<Servico>> listarServicos() {
        List<Servico> servicos = servicoService.listarServicos();
        return ResponseEntity.ok(servicos);
    }

    @PostMapping
    public ResponseEntity<Servico> criarServico(@RequestBody Servico servico) {
        Servico novoServico = servicoService.criarServico(servico);
        return ResponseEntity.ok(novoServico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServico(@PathVariable Long id) {
        servicoService.deletarServico(id);
        return ResponseEntity.noContent().build();
    }
}
