package petShop.api.domain.produto;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import petShop.api.domain.venda.Venda;

@Table(name = "produtos")
@Entity(name = "Produtos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String codigo;
    private int valorCompra;
    private int valorVenda;
    private String categoria;
    @Enumerated(EnumType.STRING)
    private UnidadeDeMedida unidadeDeMedida;
    private String quantidade;
    private String minEstoque;
    private boolean ativo;

    public Produto(DadosCadastroProduto dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.codigo = dados.codigo();
        this.valorCompra = Integer.parseInt(dados.valorCompra());
        this.valorVenda = Integer.parseInt(dados.valorVenda());
        this.unidadeDeMedida = dados.unidadeDeMedida();
        this.quantidade =  dados.quantidade();
        this.minEstoque =  dados.minEstoque();
        this.categoria = dados.categoria();
    }

      public void atualizarInformacoes(@Valid DadosAtualizarProduto dados) {
       if(dados.nome() != null){
           this.nome = dados.nome();
       }
          if(dados.quantidade() != null){
              this.quantidade = dados.quantidade();
          }
          if(dados.categoria() != null){
              this.categoria = dados.categoria();
          }
          if(dados.unidadeDeMedida() != null){
              this.unidadeDeMedida = UnidadeDeMedida.valueOf(dados.unidadeDeMedida());
          }
          if(dados.valorCompra() != null){
              this.valorCompra = Integer.parseInt(dados.valorCompra());
          }
          if(dados.valorVenda() != null){
              this.valorVenda = Integer.parseInt(dados.valorVenda());
          }
          if(dados.codigo() != null){
              this.codigo = dados.codigo();
          }
          if(dados.minEstoque() != null){
              this.minEstoque = dados.minEstoque();
          }
    }

    public void excluir() {
        this.ativo = false;
    }

}