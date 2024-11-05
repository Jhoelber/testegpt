package petShop.api.domain.usuario;

public record DadosAutenticacao(String login, String senha, UserRole role) {
}
