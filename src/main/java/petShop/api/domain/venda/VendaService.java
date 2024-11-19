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
import petShop.api.domain.itensVenda.ItensVenda;
import petShop.api.domain.itensVenda.ItensVendaRepository;

import java.math.BigDecimal;
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
    @Autowired
    private ItensVendaRepository itensVendaRepository;

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


        Venda novaVenda = new Venda(
                cliente,
                funcionario,
                quantidade,
                LocalDateTime.now(),
                formaPagamento,
                valorTotal,
                tipoVenda,
                new ArrayList<>()
        );

        // Cria e associa os itens de venda (produtos)
        if (produtoIds != null) {
            for (Long produtoId : produtoIds) {
                Produto produto = produtoRepository.findById(produtoId)
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

                // Cria o item de venda para o produto
                ItensVenda itemProduto = new ItensVenda();
                itemProduto.setVenda(novaVenda);
                itemProduto.setProduto(produto);
                itemProduto.setQuantidade(quantidade);

                BigDecimal valorUnitarioProduto = BigDecimal.valueOf(produto.getValorVenda()).setScale(2, BigDecimal.ROUND_HALF_UP);
                itemProduto.setValorUnitario(valorUnitarioProduto);

                BigDecimal valorTotalProduto = valorUnitarioProduto.multiply(BigDecimal.valueOf(quantidade)).setScale(2, BigDecimal.ROUND_HALF_UP);
                itemProduto.setValorTotal(valorTotalProduto);

                // Adicionar o item à lista de itens da venda
                novaVenda.getItensVenda().add(itemProduto);
            }
        }

        // Criar e associar os itens de venda (servicos)
        if (servicoIds != null) {
            for (Long servicoId : servicoIds) {
                Servico servico = servicoRepository.findById(servicoId)
                        .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

                // Criar o item de venda para o servico
                ItensVenda itemServico = new ItensVenda();
                itemServico.setVenda(novaVenda);
                itemServico.setServico(servico);
                itemServico.setQuantidade(quantidade);

                BigDecimal valorUnitarioServico = BigDecimal.valueOf(servico.getPreco()).setScale(2, BigDecimal.ROUND_HALF_UP);
                itemServico.setValorUnitario(valorUnitarioServico);

                BigDecimal valorTotalServico = valorUnitarioServico.multiply(BigDecimal.valueOf(quantidade)).setScale(2, BigDecimal.ROUND_HALF_UP);
                itemServico.setValorTotal(valorTotalServico);

                // Adicionar o item à lista de itens da venda
                novaVenda.getItensVenda().add(itemServico);
            }
        }

        // Salvar a venda e os itens no banco de dados
        return vendaRepository.save(novaVenda);
    }
}
