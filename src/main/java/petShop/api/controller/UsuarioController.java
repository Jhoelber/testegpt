package petShop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import petShop.api.domain.usuario.DadosRegistroUsuario;
import petShop.api.domain.usuario.UserRole;
import petShop.api.domain.usuario.Usuario;
import petShop.api.domain.usuario.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid DadosRegistroUsuario dados) {
        // Verifica se o login já existe
        if (usuarioRepository.existsByLogin(dados.login())) {
            return ResponseEntity.badRequest().body("Usuário com esse login já existe.");
        }

        // Tenta converter a string do role para UserRole
        UserRole userRole;
        try {
            userRole = UserRole.valueOf(dados.role());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Role inválida.");
        }

        // Codifica a senha
        String senhaCodificada = passwordEncoder.encode(dados.senha());

        // Cria e salva o novo usuário
        Usuario novoUsuario = new Usuario(null, dados.login(), senhaCodificada, userRole);
        usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok("Usuário registrado com sucesso");
    }



}
