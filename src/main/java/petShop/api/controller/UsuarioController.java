package petShop.api.controller;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import petShop.api.domain.usuario.Usuario;
import petShop.api.infra.security.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@Valid @RequestBody Usuario usuario) {
        usuarioService.registrarUsuario(usuario.getLogin(), usuario.getSenha());
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso.");
    }

}
