package petShop.api.infra.security;

import petShop.api.domain.usuario.UserRole;
import petShop.api.domain.usuario.Usuario;
import petShop.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import petShop.api.domain.endereco.Endereco;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void registrarUsuario(String login,
                                 String senha,
                                 UserRole role,
                                 String nome,
                                 String telefone,
                                 String cargo,
                                 String email,
                                 String cpf,

                                 Endereco endereco) {

        if (usuarioRepository.existsByLogin(login)) {
            throw new RuntimeException("Usuário já cadastrado");
        }

        String senhaCodificada = passwordEncoder.encode(senha);

        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(login);

        novoUsuario.setSenha(senhaCodificada);
        novoUsuario.setRole(role);
        novoUsuario.setNome(nome);
        novoUsuario.setTelefone(telefone);
        novoUsuario.setCargo(cargo);
        novoUsuario.setEmail(email);
        novoUsuario.setCpf(cpf);
        novoUsuario.setEndereco(endereco);
        novoUsuario.setAtivo(true);


        usuarioRepository.save(novoUsuario);
    }



}

