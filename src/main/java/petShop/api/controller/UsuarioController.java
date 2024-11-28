package petShop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import petShop.api.domain.endereco.Endereco;
import petShop.api.domain.endereco.DadosEndereco;
import petShop.api.domain.endereco.EnderecoRepository;
import petShop.api.domain.funcionario.DadosListagemUsuario;
import petShop.api.domain.usuario.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid DadosRegistroUsuario dados) {

        if (usuarioRepository.existsByEmail(dados.email())) {
            return ResponseEntity.badRequest().body("Usuário com esse email já existe.");
        }

        UserRole userRole;
        try {
            userRole = UserRole.valueOf(dados.role());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Role inválida.");
        }

        String senhaCodificada = passwordEncoder.encode(dados.senha());

        Endereco dadosEndereco = dados.endereco();
        if (dadosEndereco.getUf() == null || dadosEndereco.getUf().isEmpty()) {
            return ResponseEntity.badRequest().body("O campo 'uf' não pode ser nulo ou vazio.");
        }

        Endereco novoEndereco = dadosEndereco;

        enderecoRepository.save(novoEndereco);

        Usuario novoUsuario = new Usuario(
                null,
                dados.email(),
                senhaCodificada,
                userRole,
                dados.nome(),
                dados.telefone(),
                dados.cargo(),
                dados.cpf(),
                novoEndereco,
                true
        );

        usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok("Usuário registrado com sucesso");
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping
    public Page<DadosListagemUsuario> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return usuarioRepository.findAll(paginacao).map(DadosListagemUsuario::new);
    }

    @PutMapping("/{id}")
    @Transactional
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> atualizar(@PathVariable long id, @RequestBody @Valid DadosAtualizarUsuario dados) {
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.atualizarInformacoes(dados);
        return ResponseEntity.ok("Usuário atualizado com sucesso");
    }
    @DeleteMapping("/{id}")
    @Transactional
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> excluir(@PathVariable long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
        return ResponseEntity.ok("Usuário excluído com sucesso");
    }

}