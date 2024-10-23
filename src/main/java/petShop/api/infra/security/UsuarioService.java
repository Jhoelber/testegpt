package petShop.api.infra.security;

import petShop.api.domain.usuario.Usuario;
import petShop.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void registrarUsuario(String login, String senha) {

        String senhaCodificada = passwordEncoder.encode(senha);


        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(login);
        novoUsuario.setSenha(senhaCodificada);


        usuarioRepository.save(novoUsuario);
    }
}
