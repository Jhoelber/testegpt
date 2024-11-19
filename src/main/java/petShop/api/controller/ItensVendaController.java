package petShop.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petShop.api.domain.itensVenda.ItensVenda;
import petShop.api.domain.itensVenda.ItensVendaRepository;


import java.util.List;

@RestController
@RequestMapping("/api/itens-venda")
@RequiredArgsConstructor
public class ItensVendaController {

    private final ItensVendaRepository itensVendaRepository;

    // Buscar todos os itens de venda
    @GetMapping
    public List<ItensVenda> listarTodos() {
        return itensVendaRepository.findAll();
    }

    // Buscar item de venda por ID
    @GetMapping("/{id}")
    public ResponseEntity<ItensVenda> buscarPorId(@PathVariable Long id) {
        return itensVendaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar novo item de venda
    @PostMapping
    public ResponseEntity<ItensVenda> criar(@RequestBody ItensVenda novoItem) {
        ItensVenda itemVendaSalvo = itensVendaRepository.save(novoItem);
        return ResponseEntity.ok(itemVendaSalvo);
    }

    // Atualizar item de venda
    @PutMapping("/{id}")
    public ResponseEntity<ItensVenda> atualizar(@PathVariable Long id, @RequestBody ItensVenda itemAtualizado) {
        return itensVendaRepository.findById(id).map(item -> {
            item.setProduto(itemAtualizado.getProduto());
            item.setServico(itemAtualizado.getServico());
            item.setQuantidade(itemAtualizado.getQuantidade());
            item.setValorUnitario(itemAtualizado.getValorUnitario());
            item.setValorTotal(itemAtualizado.getValorTotal());
            ItensVenda atualizado = itensVendaRepository.save(item);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }
}
