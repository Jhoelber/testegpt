package petShop.api.domain.produto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroProduto(

        @NotBlank
        String nome,
        @NotBlank
        String categoria,
        @NotBlank
        String valorVenda,
        @NotBlank
//      @Pattern(regexp = "\\d{4,6}")
        String valorCompra,
        @NotBlank
//      @Pattern(regexp = "\\d{4,6}")
        String quantidade,

        UnidadeDeMedida unidadeDeMedida,
        @NotBlank
//      @Pattern(regexp = "\\d{4,6}")
        String codigo,
        @NotBlank
//      @Pattern(regexp = "\\d{4,6}")
        String minEstoque

) {
}