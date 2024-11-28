package petShop.api.domain.usuario;

public record DadosAutenticacao(String email, String senha, UserRole role) {
}
