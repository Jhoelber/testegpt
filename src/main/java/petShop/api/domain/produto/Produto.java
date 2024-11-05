package petShop.api.domain.produto;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import petShop.api.domain.venda.Venda;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String codigo; //codigo
    private String valorCompra;//valorCompra
    private String valorVenda;//valorVenda
    private Boolean ativo;
    private String categoria;
    @Enumerated(EnumType.STRING)
    private UnidadeDeMedida unidadeDeMedida; //categoria
    private String quantidade;  //quantidade
    private String minEstoque; //MinEmEstoque
    // Método para associar a venda ao produto
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    public Produto(DadosCadastroProduto dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.codigo = dados.codigo();
        this.valorCompra = dados.valorCompra();
        this.valorVenda = dados.valorVenda();
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
              this.valorCompra = dados.valorCompra();
          }
          if(dados.valorVenda() != null){
              this.valorVenda = dados.valorVenda();
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