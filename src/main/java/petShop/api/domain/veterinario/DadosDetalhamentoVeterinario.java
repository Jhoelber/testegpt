package petShop.api.domain.veterinario;

import petShop.api.domain.endereco.Endereco;

public record DadosDetalhamentoVeterinario(
        Long id,
        String nome,
        String email,
        String crm,
        String cpf,
        String telefone,
        Especialidade especialidade,
        Endereco endereco) {

    public DadosDetalhamentoVeterinario(Veterinario veterinario) {
        this(
                veterinario.getId(),
                veterinario.getNome(),
                veterinario.getEmail(),
                veterinario.getCrm(),
                veterinario.getCpf(),
                veterinario.getTelefone(),
                veterinario.getEspecialidade(),
                veterinario.getEndereco());
    }
}
