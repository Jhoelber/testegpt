package petShop.api.domain.animal;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

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
        LocalDate dataNascimento,

        @NotBlank
        String cor,

        @NotBlank
        String descricao,

        @NotBlank
        String raca,

        @NotBlank
        String peso,

        Long clientesId
) {
}
