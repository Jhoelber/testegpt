package petShop.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import petShop.api.domain.cliente.Cliente;
import petShop.api.domain.funcionario.Funcionario;
import petShop.api.domain.produto.Produto;
import petShop.api.domain.servico.Servico;
import petShop.api.domain.venda.DadosVenda;
import org.springframework.http.HttpStatus;
import petShop.api.domain.venda.Venda;
import petShop.api.domain.venda.VendaRepository;
import petShop.api.domain.cliente.ClienteRepository;
import petShop.api.domain.funcionario.FuncionarioRepository;
import petShop.api.domain.produto.ProdutoRepository;
import petShop.api.domain.servico.ServicoRepository;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Venda> criarVenda(@RequestBody @Valid DadosVenda dadosVenda) {

        // Busca as entidades relacionadas pelos IDs fornecidos
        Cliente cliente = clienteRepository.findById(dadosVenda.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Funcionario funcionario = funcionarioRepository.findById(dadosVenda.getFuncionarioId())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        Venda novaVenda = new Venda();
        novaVenda.setCliente(cliente);
        novaVenda.setFuncionario(funcionario);
        novaVenda.setFormaPagamento(dadosVenda.getFormaPagamento());
        novaVenda.setValorTotal(dadosVenda.getValorTotal());



        vendaRepository.save(novaVenda);

        return ResponseEntity.status(HttpStatus.CREATED).body(novaVenda);
    }


}