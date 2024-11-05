package petShop.api.domain.venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petShop.api.domain.cliente.Cliente;
import petShop.api.domain.cliente.ClienteRepository;
import petShop.api.domain.funcionario.Funcionario;
import petShop.api.domain.funcionario.FuncionarioRepository;
import petShop.api.domain.produto.Produto;
import petShop.api.domain.produto.ProdutoRepository;
import petShop.api.domain.servico.Servico;
import petShop.api.domain.servico.ServicoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Service
public class VendaService {

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

    public Venda criarVenda(Long clienteId,
                            Long funcionarioId,
                            List<Long> produtoIds,
                            List<Long> servicoIds,
                            int quantidade,
                            String formaPagamento,
                            double valorTotal,
                            TipoVenda tipoVenda) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        List<Produto> produtos = new ArrayList<>();
        if (produtoIds != null) {
            for (Long produtoId : produtoIds) {
                Produto produto = produtoRepository.findById(produtoId)
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
                produtos.add(produto); // Adiciona o produto à lista
            }
        }

        List<Servico> servicos = new ArrayList<>();
        if (servicoIds != null) {
            for (Long servicoId : servicoIds) {
                Servico servico = servicoRepository.findById(servicoId)
                        .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
                servicos.add(servico); // Adiciona o serviço à lista
            }
        }

        // Criar a nova venda usando os objetos encontrados
        Venda novaVenda = new Venda(
                cliente,
                funcionario,
                produtos,  // Passa a lista de produtos
                servicos,  // Passa a lista de serviços
                quantidade,
                LocalDateTime.now(),
                formaPagamento,
                valorTotal,
                tipoVenda
        );

        // Salvar a venda no banco de dados
        return vendaRepository.save(novaVenda);
    }
}
