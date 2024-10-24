package petShop.api.domain.animal;

import jakarta.validation.constraints.NotBlank;


public record DadosCadastroAnimal(
        @NotBlank
         String nome,
        @NotBlank
         String especie,
        @NotBlank
         String vacina,
        @NotBlank
         String sexo,
        @NotBlank
         String dataNascimento,
        @NotBlank
         String cor,
        @NotBlank
         String descricao,
        @NotBlank
         String raca,
        @NotBlank
         String peso,

        petShop.api.domain.cliente.Cliente cliente) {
}
